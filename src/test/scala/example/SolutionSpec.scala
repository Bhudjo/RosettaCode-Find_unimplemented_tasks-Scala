package example

import example.Solution.{Category, Query, Task}
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods._
import org.scalatest._

class SolutionSpec extends WordSpec with Matchers {

  "subtract one set from another in the same list" in {
    val x = List(Set(1, 2, 3, 4, 5, 6), Set(1, 2, 3, 10))
      .foldRight(Set.empty[Int])((acc: Set[Int], ele: Set[Int]) => acc -- ele)
    x shouldBe Set(4, 5, 6)
  }

  implicit val formats = DefaultFormats

  "extract json" in {
    val actual = parse("""{  "batchcomplete": "",  "continue": {    "cmcontinue": "page|5045524d55544154494f4e532f52414e4b204f462041205045524d55544154494f4e|12454",    "continue": "-||"  },  "query": {    "categorymembers": [      { "pageid": 2151, "ns": 0, "title": "100 doors" },      { "pageid": 17706, "ns": 0, "title": "2048" },      { "pageid": 4938, "ns": 0, "title": "24 game" },      { "pageid": 4940, "ns": 0, "title": "24 game/Solve" },      {        "pageid": 13385,        "ns": 0,        "title": "9 billion names of God the integer"      },      { "pageid": 2663, "ns": 0, "title": "99 Bottles of Beer" },      { "pageid": 5295, "ns": 0, "title": "99 Bottles of Beer/Scala" },      { "pageid": 6812, "ns": 0, "title": "A+B" },      { "pageid": 17061, "ns": 0, "title": "ABC Problem" },      { "pageid": 3131, "ns": 0, "title": "Abstract type" },      {        "pageid": 18392,        "ns": 0,        "title": "Abundant, deficient and perfect number classifications"      },      { "pageid": 5255, "ns": 0, "title": "Accumulator factory" },      { "pageid": 3050, "ns": 0, "title": "Ackermann function" },      { "pageid": 2983, "ns": 0, "title": "Active Directory/Connect" }    ]  }}""").extract[Query]

    actual.query.categorymembers should contain allElementsOf Seq(Task(2151,"100 doors"), Task(17706,"2048"), Task(4938,"24 game"))

    println(actual)
  }

  "extract simple json " in {
    val actual = parse("""{ "pageid": 2151, "ns": 0, "title": "100 doors", "jesul" :876 }""").extract[Task]
    actual shouldBe Task(2151, "100 doors")
  }

}
