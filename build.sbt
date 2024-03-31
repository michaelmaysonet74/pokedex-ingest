import Dependencies.*
import play.sbt.PlayImport.PlayKeys.*
import com.typesafe.sbt.packager.docker.*

ThisBuild / scalaVersion := "2.13.8"
ThisBuild / version := "1.0.0"
ThisBuild / organization := "com.michaelmaysonet74"
ThisBuild / organizationName := "michaelmaysonet74"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, CalibanPlugin)
  .settings(
    name := """pokedex-ingest""",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      macwireMacros % Provided,
      macwireUtil,
      mongoDb,
      calibanClient,
      weePickle,
      enumeratum,
      ws
    ),
    playDefaultPort := 9000
  )
  .settings(
    Compile / caliban / calibanSettings += calibanSetting(file("pokedex-schema.graphql"))(
      _.clientName("PokedexSchema").packageName("clients.schemas")
    )
  )
  .settings(
    dockerExposedPorts ++= Seq(9000),
    dockerChmodType := DockerChmodType.UserGroupWriteExecute,
    dockerPermissionStrategy := DockerPermissionStrategy.CopyChown,
    dockerEnvVars := Map(
      "APPLICATION_SECRET" -> sys.env.getOrElse("APPLICATION_SECRET", "")
    )
  )
