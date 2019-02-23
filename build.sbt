import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "RosettaCode-Find_unimplemented_tasks-Scala",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "org.json4s"%%"json4s-native"%"3.6.0",
      "com.softwaremill.sttp"%%"core"%"1.5.11",
      "com.softwaremill.sttp"%%"json4s"%"1.5.11",
    )
  )