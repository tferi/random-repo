package com.tothferenc.airdb.parsing

import java.io.InputStream

import scala.collection.mutable.ArrayBuffer

object Csv {
  def fields(line: String) = {
    val raw: Array[String] = line.split(',') //TODO this does not care if commas are in strings.
    raw.map { field =>
      field.trim.stripPrefix("\"").stripSuffix("\"")
    }
  }

  def parseStream[T: CsvLineParser](stream: InputStream): ArrayBuffer[T] = {
    new LineByLineCsvParser[T]().parseStream(stream)
  }
}
