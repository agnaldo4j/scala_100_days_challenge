val scalaTestVersion = "3.2.9"
val catsVersion = "2.6.1"
val zioVersion = "1.0.8"

ThisBuild / organization := "com.thelambdadev"
ThisBuild / scalaVersion := "3.0.0"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / name         := "scala100DaysChallange"
ThisBuild / javacOptions ++= Seq("-source", "1.11", "-target", "1.11")

lazy val root = project
  .in(file("."))
  .settings(
    version := "0.1.0",

    libraryDependencies += "dev.zio" %% "zio" % zioVersion,
    libraryDependencies += "dev.zio" %% "zio-streams" % zioVersion,
    libraryDependencies += "org.scalactic" %% "scalactic" % scalaTestVersion,
    libraryDependencies += "org.scalatest" %% "scalatest-freespec" % scalaTestVersion % "test",
    libraryDependencies += "org.typelevel" %% "cats-core" % catsVersion,
  )
