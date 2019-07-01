package jp.iwmat.httpserver.http

import scala.util._
import jp.iwmat.httpserver.syntax._

case class HttpRequest(inputs: List[String]) extends OptionImplicit {

  private[http] val statusLine: Try[StatusLine] = {
    val unit = Success(())
    for {
      statusLines <- inputs.headOption.map(_.split(" ")).toTry(HttpRequest.errors.EmptyRequest)
      _           <- if (statusLines.length == 3) unit else Failure(HttpRequest.errors.InvalidProtocol)
      method      <- Method.valueOf(statusLines(0)).toTry(HttpRequest.errors.InvalidProtocol)
      version     <- Version.valueOf(statusLines(2)).toTry(HttpRequest.errors.InvalidProtocol)
    } yield StatusLine(method, statusLines(1), version)
  }

  def isValid: Boolean = statusLine.isSuccess
}

object HttpRequest {
  object errors {
    case object EmptyRequest extends Exception("client request is empty")
    case object InvalidProtocol extends Exception("invalid protocol")
  }
}
