import sbt._
import Keys._

object Build extends Build {
  val Organization = "uclmr"
  val Name = "low-rank-logic"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.10.4"

  lazy val wolfeCore = ProjectRef(uri("https://github.com/wolfe-pack/wolfe.git"), "wolfe-core")
  lazy val wolfeUtil = ProjectRef(uri("https://github.com/wolfe-pack/wolfe.git"), "wolfe-util")
  //lazy val wolfeNLP = ProjectRef(uri("https://github.com/wolfe-pack/wolfe.git"), "wolfe-nlp")

  lazy val root = Project(
    "low-rank-logic",
    file("."),
    settings = Defaults.defaultSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      libraryDependencies ++= Seq(
        "net.sandrogrzicic" %% "scalabuff-compiler" % "1.3.6",
        "net.sandrogrzicic" %% "scalabuff-runtime" % "1.3.6",
        "com.google.protobuf" % "protobuf-java" % "2.3.0"
      )
    )
  ) dependsOn (
    wolfeCore % "test->test;compile->compile",
    wolfeUtil % "test->test;compile->compile"//,
    //wolfeNLP % "test->test;compile->compile"
  )
}