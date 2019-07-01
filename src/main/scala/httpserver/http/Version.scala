package jp.iwmat.httpserver.http

import jp.iwmat.httpserver.{ Enum, EnumCompanion }

sealed abstract class Version(val code: String) extends Enum[String]
object Version extends EnumCompanion[String, Version] {
  case object HTTP_09 extends Version("HTTP/0.9")
  case object HTTP_10 extends Version("HTTP/1.0")
  case object HTTP_11 extends Version("HTTP/1.1")
  case object HTTP_2  extends Version("HTTP/2")
  case object HTTP_3  extends Version("HTTP/3")
  final val values = Seq(HTTP_09, HTTP_10, HTTP_11, HTTP_2, HTTP_3)
}
