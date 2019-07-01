package jp.iwmat.httpserver.http

import java.io._
import java.net.{ ServerSocket, Socket }
import scala.concurrent.ExecutionContext.Implicits.global
import org.scalatest._
import org.mockito.MockitoSugar

class HttpConnectionSpec extends WordSpec with MustMatchers with MockitoSugar {

  def createMockConnection() = {
    val mockSocket = mock[Socket]
    val mockServerSocket = mock[ServerSocket]
    when(mockServerSocket.accept()) thenReturn mockSocket
    HttpConnection(mockServerSocket)
  }

  "readLines" when {

    def readLines(input: String): List[String] = {
      val connection = createMockConnection()
      val reader = new BufferedReader(new StringReader(input))
      val actual = connection.readLines(Nil, reader)
      reader.close()
      actual
    }

    "reader has \"hello\\r\\nworld\"" must {
      "return 2 elements" in {
        val input = "hello\r\nworld"
        val actual = readLines(input)
        actual mustBe List("hello", "world")
      }
    }

    "reader has \"hello\"" must {
      "return 1 element" in {
        val input = "hello"
        val actual = readLines(input)
        actual mustBe List("hello")
      }
    }

    "reader has empty string" must {
      "return 0 element" in {
        val input = ""
        val actual = readLines(input)
        actual mustBe Nil
      }
    }
  }
}
