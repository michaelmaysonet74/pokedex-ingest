import sbt.*

object Dependencies {

  private val macwireVersion = "2.5.9"
  private val calibanVersion = "2.5.3"

  lazy val scalaTest = "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1"
  lazy val macwireMacros = "com.softwaremill.macwire" %% "macros" % macwireVersion
  lazy val macwireUtil = "com.softwaremill.macwire" %% "util" % macwireVersion
  lazy val calibanClient = "com.github.ghostdogpr" %% "caliban-client" % calibanVersion
  lazy val weePickle = "com.rallyhealth" %% "weepickle-v1" % "1.9.1"
  lazy val mongoDb = "org.mongodb.scala" %% "mongo-scala-driver" % "4.11.1"
  lazy val enumeratum = "com.beachape" %% "enumeratum" % "1.7.3"

}
