val scalaTestVersion = "3.2.9"
val catsVersion = "2.6.1"
val zioVersion = "1.0.9"
val zioIteropCats = "3.1.1.0"
val http4sVersion = "1.0.0-M23"
val catsEffectsVersion = "3.1.1"

ThisBuild / organization := "com.thelambdadev"
ThisBuild / scalaVersion := "3.0.0"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / name := "scala100DaysChallange"
ThisBuild / javacOptions ++= Seq("-source", "1.11", "-target", "1.11")

lazy val http4sDependency = Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "dev.zio" %% "zio-interop-cats" % zioIteropCats
)

lazy val root = project
  .in(file("."))
  .settings(
    libraryDependencies += "dev.zio" %% "zio" % zioVersion,
    libraryDependencies += "dev.zio" %% "zio-streams" % zioVersion,
    libraryDependencies += "org.scalactic" %% "scalactic" % scalaTestVersion,
    libraryDependencies += "org.scalatest" %% "scalatest-freespec" % scalaTestVersion % "test",
    libraryDependencies += "org.typelevel" %% "cats-core" % catsVersion,
    libraryDependencies ++= http4sDependency
  )
