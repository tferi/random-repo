package com.tothferenc.airdb.parsing

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.function.Consumer

import com.tothferenc.airdb.util.Using

import scala.collection.mutable.ArrayBuffer

trait CsvStreamParser[T] {
  def parseStream(stream: InputStream): ArrayBuffer[T]
}

class LineByLineCsvParser[T: CsvLineParser] extends CsvStreamParser[T] {
  override def parseStream(stream: InputStream): ArrayBuffer[T] = {
    Using(new BufferedReader(new InputStreamReader(stream))) { reader =>
      val parsedItems = new ArrayBuffer[T]()
      reader.lines().skip(1).forEach(new Consumer[String] {
        override def accept(line: String): Unit = {
          val parsed = implicitly[CsvLineParser[T]].parseLine(line)
          parsedItems += parsed
        }
      })
      parsedItems
    }
  }
}