package com.tothferenc.airdb.repo

import com.tothferenc.airdb.model.Airport
import com.tothferenc.airdb.parsing.Csv

class AirportRepo(resourceUrl: String) {

  private val airports = Csv.parseStream[Airport](getClass.getResourceAsStream(resourceUrl)).toList

  private val airportsByCountryCode = airports.groupBy(_.countryCode)

  def byCountryCode(countryCode: String): List[Airport] =
    airportsByCountryCode.getOrElse(countryCode, Nil)

}
