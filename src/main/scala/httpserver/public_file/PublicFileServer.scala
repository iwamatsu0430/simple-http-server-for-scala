package jp.iwmat.httpserver.public_file

import scala.concurrent.{ ExecutionContext, Future }
import wvlet.log.Logger
import jp.iwmat.httpserver.Server
import jp.iwmat.httpserver.http._

class PublicFileServer(implicit val ec: ExecutionContext) extends Server {
  def handle(connection: HttpConnection): Future[Unit] = {
    val future = for {
      request <- connection.readRequest()
      file     = PublicFile(request) if request.isValid
      _       <- connection.send(file.toResponse)
    } yield ()
    future.recover {
      case error =>
        ()
    }
  }
}
