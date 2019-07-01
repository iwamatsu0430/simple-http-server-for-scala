package jp.iwmat.httpserver

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext
import wvlet.airframe._
import jp.iwmat.httpserver.public_file.PublicFileServer

object Main {
  def main(args: Array[String]): Unit = {
    newDesign
      .bind[Server]
      .to[PublicFileServer]
      .bind[ExecutionContext]
      .toInstance(ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(10)))
      .build[Server](_.boot())
  }
}
