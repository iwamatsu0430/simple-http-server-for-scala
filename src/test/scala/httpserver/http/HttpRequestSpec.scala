package jp.iwmat.httpserver.http

import scala.util._
import org.scalatest._
import org.mockito.MockitoSugar

class HttpRequestSpec extends WordSpec with MustMatchers with MockitoSugar {
  "statusLine" when {
    "inputs is Nil" must {
      "return Failure" in {
        HttpRequest(Nil).statusLine.isFailure mustBe true
      }
    }

    "inputs is not Nil" when {
      "head line is 'INVALID FORMAT DESU YO'" must {
        "return Failure" in {
          val statusLine = "INVALID FORMAT DESU YO"
          HttpRequest(List(statusLine)).statusLine.isFailure mustBe true
        }
      }

      "head line is 'GET / HTTP/1.1'" must {
        "return Success(StatusLine(GET, /, HTTP/1.1))" in {
          val statusLine = "GET / HTTP/1.1"
          HttpRequest(List(statusLine)).statusLine mustBe Success(StatusLine(Method.Get, "/", Version.HTTP_11))
        }
      }
    }
  }

  "isValid" when {
    "statusLine = Success" must {
      "return true" in {
        HttpRequest(List("GET / HTTP/1.1")).isValid mustBe true
      }
    }

    "statusLine = Failure" must {
      "return false" in {
        HttpRequest(List("INVALID")).isValid mustBe false
      }
    }
  }
}
