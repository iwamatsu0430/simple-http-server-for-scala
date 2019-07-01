ThisBuild / scalaVersion     := "2.13.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "jp.iwmat"
ThisBuild / organizationName := "httpserver"

lazy val root = (project in file("."))
  .settings(
    name := "simple-http-server-for-scala",
    libraryDependencies ++= Seq(
      "org.typelevel"       %% "cats-core"      % "2.0.0-M4",
      "org.typelevel"       %% "cats-effect"    % "2.0.0-M4",
      "org.wvlet.airframe"  %% "airframe"       % "19.6.1",
      "org.wvlet.airframe"  %% "airframe-log"   % "19.6.1",
      "org.scalatest"       %% "scalatest"      % "3.0.8"   % Test,
      "org.mockito"         %% "mockito-scala"  % "1.5.11"  % Test
    )
  )
