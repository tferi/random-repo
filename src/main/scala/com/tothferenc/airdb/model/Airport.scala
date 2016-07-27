package com.tothferenc.airdb.model

import com.tothferenc.airdb.parsing.Csv
import com.tothferenc.airdb.parsing.CsvLineParser

final case class Airport(
  ident: String,
  name: String,
  countryCode: String
)

object Airport {

  class CsvParser(iIDent: Int, iName: Int, iCountryCode: Int) extends CsvLineParser[Airport] {
    override def parseLine(line: String): Airport = {
      val fields = Csv.fields(line)
      Airport(fields(iIDent), fields(iName), fields(iCountryCode))
    }
  }

  implicit val csvParser = new CsvParser(1, 3, 8)
}