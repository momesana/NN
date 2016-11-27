val cats        = "org.typelevel" %% "cats" % "0.8.1"
val scalactic   = "org.scalactic" %% "scalactic" % "3.0.1"
val scalatest   = "org.scalatest" %% "scalatest" % "3.0.1" % "test"
val scalaCheck  = "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
val mockito     = "org.mockito" % "mockito-all" % "1.9.5"
val scalafx     = "org.scalafx" %% "scalafx" % "8.0.102-R11"

scalacOptions += "-target:jvm-1.8"

initialize := {
  val required = "1.8"
  val current  = sys.props("java.specification.version")
  assert(current == required, s"Unsupported JDK: java.specification.version $current != $required")
}

lazy val commonSettings = Seq(
  organization := "edu.uni-bremen.nn",
  version := "0.5",
  scalaVersion := "2.12.0"
)

unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/ext/jfxrt.jar"))

unmanagedJars in Compile += {
  val ps = new sys.SystemProperties
  val jh = ps("java.home")
  Attributed.blank(file(jh) / "lib/ext/jfxrt.jar")
}

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "ueb05",
    libraryDependencies ++= Seq(scalafx, cats, scalactic, scalatest, scalaCheck, mockito)
  )
