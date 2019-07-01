package jp.iwmat.httpserver.http

import scala.util._
import org.scalatest._
import org.mockito.MockitoSugar

class HttpRequestSpec extends WordSpec with MustMatchers with MockitoSugar {
  "statusLines" when {
    "inputs is Nil" must {
      "return Failure" in {
        HttpRequest(Nil).statusLines.isFailure mustBe true
      }
    }

    "inputs is not Nil" when {
      "head line is 'GET'" must {
        "return Failure" in {
          val statusLine = "GET"
          HttpRequest(List(statusLine)).statusLines.isFailure mustBe true
        }
      }

      "head line is 'GET /'" must {
        "return Failure" in {
          // val statusLine = "GET /"
          // HttpRequest(List(statusLine)).statusLines.isFailure mustBe true
        }
      }

      "head line is 'GET / HTTP/1.1'" must {
        // "return Failure" in {
        //   val statusLine = "GET /"
        //   HttpRequest(List(statusLine)).statusLines.isFailure mustBe true
        // }
      }
    }
  }
}
