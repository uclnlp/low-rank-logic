import sbt._
import Keys._

object Build extends Build {
  val Organization = "uclmr"
  val Name = "low-rank-logic"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.10.4"


  lazy val wolfeCore = ProjectRef(file("./wolfe"), "wolfe-core")
  lazy val wolfeUtil = ProjectRef(file("./wolfe"), "wolfe-util")

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
      ),
      commands ++= Seq(vmargs),
      fork in run := true //use a fresh JVM for sbt run
    )
  ) dependsOn (
    wolfeCore % "test->test;compile->compile",
    wolfeUtil % "test->test;compile->compile"//,
  )

  //utility methods
  def vmargs = Command.args("vmargs", "<name>") {
    (state, args) =>
      val javaRunOptions = args.mkString(" ")
      println("Applying JVM arguments: " + javaRunOptions)
      Project.extract(state).append(javaOptions := Seq(javaRunOptions), state)
  }
}