package jp.iwmat.httpserver.http

import scala.util._

case class HttpRequest(inputs: List[String]) {

  private[http] val statusLines: Try[String] = {
    val unit = Success(())
    for {
      _           <- if (inputs.nonEmpty) unit else Failure(HttpRequest.errors.EmptyRequest())
      statusLines <- inputs.headOption.map(_.split(" ")).toRight(new Exception("")).toTry
      _           <- if (statusLines.length == 3) unit else Failure(new Exception(""))
    } yield ""
  }

  def isValid: Boolean = {
    ???
  }
}

object HttpRequest {
  object errors {
    case class EmptyRequest() extends Exception("client request is empty")
  }
}
