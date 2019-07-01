package jp.iwmat.httpserver

import org.scalatest._

class EnumSpec extends WordSpec with MustMatchers {

  sealed abstract class Meta(val code: String) extends Enum[String]
  object Meta extends EnumCompanion[String, Meta] {
    case object Foo extends Meta("foo")
    case object Bar extends Meta("bar")
    final val values = Seq(Foo, Bar)
  }

  "valueOf" when {
    "valid parameter" must {
      "return Some(Enum)" in {
        Meta.values.foreach { meta =>
          Meta.valueOf(meta.code) mustBe Some(meta)
        }
      }
    }

    "invalid parameter" must {
      "return None" in {
        Meta.valueOf("INVALID") mustBe None
      }
    }
  }
}
