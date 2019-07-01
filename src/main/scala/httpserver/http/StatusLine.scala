package jp.iwmat.httpserver.http

case class StatusLine(method: Method, path: String, version: Version)
