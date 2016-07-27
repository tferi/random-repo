package com.tothferenc.airdb.parsing

trait CsvLineParser[T] {
	def parseLine(line: String): T
}
