package jp.iwmat.httpserver.public_file

import jp.iwmat.httpserver.http._

case class PublicFile(request: HttpRequest) {
  def toResponse: HttpResponse = ???
}
