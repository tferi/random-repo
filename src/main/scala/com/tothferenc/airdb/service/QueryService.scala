package com.tothferenc.airdb.service

import com.tothferenc.airdb.model.Airport
import com.tothferenc.airdb.model.Runway
import com.tothferenc.airdb.repo._

class QueryService(
    countryRepo: CountryRepo,
    airportRepo: AirportRepo,
    runwayRepo: RunwayRepo
) {

  def query(country: String): Iterable[(Airport, Vector[Runway])] = {
    for {
      country <- countryRepo.byCode(country).orElse(countryRepo.byName(country)).toVector
      airport <- airportRepo.byCountryCode(country.code)
    } yield {
      airport -> runwayRepo.byAirportIdent(airport.ident)
    }
  }
}
