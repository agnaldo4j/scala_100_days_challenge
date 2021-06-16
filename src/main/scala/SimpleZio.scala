import zio.console._
import zio._

/*
  A ZIO[R, E, A] value is an immutable value that lazily describes a workflow or job.
  The workflow requires some environment R, and may fail with an error of type E, or
  succeed with a value of type A.
*/

//Running with zio.App
object MyApp extends zio.App {
  def run(args: List[String]) = myAppLogic.exitCode
}

//Running with zio Runtime
@main def executeZio() = {
  val runtime = Runtime.default
  runtime.unsafeRun(myAppLogic)
}

//Very simple effects with ZIO
val myAppLogic = for {
  _ <- putStrLn("Hello! what ir your name?")
  name <- getStrLn //ZIO[ENV, ERROR, DATA]
  _ <- putStrLn(s"Hello, ${name}, welcome to ZIO!")
} yield ()
