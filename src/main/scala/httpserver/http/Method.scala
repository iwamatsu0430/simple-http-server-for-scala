package jp.iwmat.httpserver.http

import jp.iwmat.httpserver.{ Enum, EnumCompanion }

sealed abstract class Method(val code: String) extends Enum[String]
object Method extends EnumCompanion[String, Method] {
  case object Get     extends Method("GET")
  case object Head    extends Method("HEAD")
  case object Post    extends Method("POST")
  case object Put     extends Method("PUT")
  case object Delete  extends Method("DELETE")
  case object Connect extends Method("CONNECT")
  case object Options extends Method("OPTIONS")
  case object Trace   extends Method("TRACE")
  case object Patch   extends Method("PATCH")
  final val values = Seq(Get, Head, Post, Put, Delete, Connect, Options, Trace, Patch)
}
