package com.tothferenc.airdb.model

import com.tothferenc.airdb.parsing.Csv
import com.tothferenc.airdb.parsing.CsvLineParser

final case class Runway(
  id: String,
  airportIdent: String,
  surface: String
)

object Runway {

  class CsvParser(iId: Int, iAirportIdent: Int, iSurface: Int) extends CsvLineParser[Runway] {
    override def parseLine(line: String): Runway = {
      val fields = Csv.fields(line)
      Runway(fields(iId), fields(iAirportIdent), fields(iSurface))
    }
  }

  implicit val csvParser = new CsvParser(0, 2, 5)
}
