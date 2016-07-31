package com.tothferenc.airdb.repo

import com.tothferenc.airdb.model.Airport
import com.tothferenc.airdb.parsing.Csv

class CsvBasedAirportRepo(resourceUrl: String) extends AirportRepo {

  private val airports = Csv.parseStream[Airport](getClass.getResourceAsStream(resourceUrl)).toVector

  private val airportsByCountryCode = airports.groupBy(_.countryCode)

  override def byCountryCode(countryCode: String): Vector[Airport] =
    airportsByCountryCode.getOrElse(countryCode, Vector.empty)

  override def countsByCountry: Map[String, Int] = airportsByCountryCode.map {
    case (country, airportList) => country -> airportList.length
  }

}
