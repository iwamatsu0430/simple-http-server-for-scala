package jp.iwmat.httpserver.http

import java.net.ServerSocket
import java.io._
import scala.annotation.tailrec
import scala.concurrent.{ ExecutionContext, Future }
import scala.util.Try
import jp.iwmat.httpserver.http._

case class HttpConnection(serverSocket: ServerSocket)(implicit ec: ExecutionContext) {

  private val socket = serverSocket.accept()
  private lazy val reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
  private lazy val writer = new BufferedOutputStream(socket.getOutputStream())

  def readRequest(): Future[HttpRequest] = {
    Future { HttpRequest(readLines(Nil, reader)) }
  }

  @tailrec
  private[http] final def readLines(acc: List[String], _reader: BufferedReader): List[String] = {
    val line = _reader.readLine()
    if (line != null)
      readLines(acc :+ line, _reader)
    else
      acc
  }

  def send(response: HttpResponse): Future[Unit] = ???

  def close(): Future[Unit] = {
    Future {
      writer.close()
      reader.close()
      socket.close()
    }
  }
}

object HttpConnection {
  def accept(serverSocket: ServerSocket)(implicit ec: ExecutionContext): Future[HttpConnection] = {
    val connectionTry = Try { new HttpConnection(serverSocket) }
    Future.fromTry { connectionTry }
  }
}
