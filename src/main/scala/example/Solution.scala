package example

object Solution {
  import com.softwaremill.sttp.json4s._
  import com.softwaremill.sttp.quick._

  implicit val serialization = org.json4s.native.Serialization

  import org.json4s.DefaultFormats

  implicit val formats = DefaultFormats

  case class Task(pageid: Int, title: String)

  case class Category(categorymembers: List[Task])

  case class Query(query: Category)

  val mozillaUserAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:66.0) Gecko/20100101 Firefox/66.0"

  List("Programming Tasks", "Scala")
    .map { title =>
      sttp
        .get(uri"http://www.rosettacode.org/mw/api.php?action=query&list=categorymembers&cmtitle=Category:${title}&cmlimit=1000&format=json")
        .header("User-Agent", mozillaUserAgent)
        .response(asJson[Query])
        .send()
        .body
    }
    .map {
      case Right(r) => r.query.categorymembers.toSet
      case Left(s) => Set.empty[Task]
    }
    .foldRight(Set.empty[Task])((acc: Set[Task], ele: Set[Task]) => acc -- ele)

}