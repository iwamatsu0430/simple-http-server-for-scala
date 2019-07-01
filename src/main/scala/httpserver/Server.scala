package jp.iwmat.httpserver

import java.net.{ InetSocketAddress, ServerSocket }
import scala.concurrent.{ ExecutionContext, Future }
import scala.util._
import cats.effect.IO
import wvlet.log.Logger
import jp.iwmat.httpserver.http._

trait Server {

  implicit val ec: ExecutionContext

  private[httpserver] val logger = Logger.of[Server]

  private[httpserver] def errorHandler(error: Throwable): IO[Unit] = {
    logger.error(error.getLocalizedMessage)
    IO.unit
  }

  def boot(): Unit = {
    val io = for {
      serverSocket <- IO { new ServerSocket() }
      _            <- IO { serverSocket.bind(new InetSocketAddress("localhost", 8000)) } handleErrorWith errorHandler
      _            <- IO { listen(serverSocket) } handleErrorWith errorHandler
      _            <- IO { serverSocket.close() }
    } yield ()
    io.unsafeRunSync()
  }

  private[httpserver] def listen(serverSocket: ServerSocket): Unit = {
    while (true) {
      val future = for {
        connection <- HttpConnection.accept(serverSocket)
        _          <- handle(connection)
        _          <- connection.close()
      } yield ()
      future.onComplete {
        case Success(_) => logger.debug(s"completed task in ${Thread.currentThread.getId}")
        case Failure(e) => logger.error(e.getLocalizedMessage)
      }
    }
  }

  protected def handle(connection: HttpConnection): Future[Unit]
}
