import zio.{ExitCode, Has, Task, ZIO, ZLayer}

case class User(val name: String, val email: String)

type UserEmailerEnv = Has[UserEmailer.Service]

object UserEmailer {
  trait Service {
    def notify(user: User, message: String): Task[Unit]
  }

  val live: ZLayer[Any, Nothing, UserEmailerEnv] = ZLayer.succeed(
    new Service {
      override def notify(user: User, message: String): Task[Unit] =
        Task {
          println(s"Sending '$message' to ${user.email}")
        }
    }
  )

  def notify(user: User, message: String): ZIO[UserEmailerEnv, Throwable, Unit] =
    ZIO.accessM(_.get.notify(user, message))
}

val myLogic: ZIO[Any, Any, ExitCode] = UserEmailer
  .notify(
    User("ZIO", "everyone@example.zio.com"),
    "Hello ZIO"
  )
  .provideLayer(UserEmailer.live)
  .catchAll(error => ZIO.succeed(error.printStackTrace()).map(_ => ExitCode.failure))
  .map { _ => ExitCode.success }

object ZLayerMain extends zio.App {
  def run(args: List[String]) = myLogic.exitCode
}
