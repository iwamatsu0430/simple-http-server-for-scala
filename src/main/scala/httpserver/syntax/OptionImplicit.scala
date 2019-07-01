package jp.iwmat.httpserver.syntax

import scala.util.Try

trait OptionImplicit {
  implicit class OptionImplicitImpl[A](o: Option[A]) {
    def toTry(e: Throwable): Try[A] = o.toRight(e).toTry
  }
}
