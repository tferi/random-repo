package com.tothferenc.airdb.repo

import com.tothferenc.airdb.model.Airport
import com.tothferenc.airdb.parsing.Csv

class AirportRepo(resourceUrl: String) {

  private val airports = Csv.parseStream[Airport](getClass.getResourceAsStream(resourceUrl)).toVector

  private val airportsByCountryCode = airports.groupBy(_.countryCode)

  def byCountryCode(countryCode: String): Vector[Airport] =
    airportsByCountryCode.getOrElse(countryCode, Vector.empty)

  def countsByCountry = airportsByCountryCode.map {
    case (country, airportList) => country -> airportList.length
  }

}
