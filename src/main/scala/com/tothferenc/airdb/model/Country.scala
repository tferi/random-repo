package com.tothferenc.airdb.model

import com.tothferenc.airdb.parsing.Csv
import com.tothferenc.airdb.parsing.CsvLineParser

final case class Country(
  code: String,
  name: String
)

object Country {

  class CsvParser(iCode: Int, iName: Int) extends CsvLineParser[Country] {
    override def parseLine(line: String): Country = {
      val fields = Csv.fields(line)
      Country(fields(iCode), fields(iName))
    }
  }

  implicit val csvParser = new CsvParser(1, 2)
}
