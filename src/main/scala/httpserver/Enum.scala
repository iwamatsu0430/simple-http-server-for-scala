package jp.iwmat.httpserver

trait Enum[A] {
  val code: A
}

trait EnumCompanion[A, B <: Enum[A]] {
  val values: Seq[B]
  def valueOf(code: A): Option[B] = values.find(_.code == code)
}
