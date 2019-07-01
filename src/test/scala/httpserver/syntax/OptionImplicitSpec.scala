package jp.iwmat.httpserver.syntax

import scala.util.{ Failure, Success }
import org.scalatest._

class OptionImplicitSpec extends WordSpec with MustMatchers with OptionImplicit {
  "toTry" when {
    "Some(a)" must {
      "return Success(a)" in {
        Some(1).toTry(new Throwable()) mustBe Success(1)
      }
    }

    "None" must {
      "return Failure(throwable)" in {
        None.toTry(new Throwable()).isFailure mustBe true
      }
    }
  }
}
