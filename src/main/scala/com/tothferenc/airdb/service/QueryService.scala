package com.tothferenc.airdb.service

import com.tothferenc.airdb.model.Airport
import com.tothferenc.airdb.model.Runway
import com.tothferenc.airdb.repo.AirportRepo
import com.tothferenc.airdb.repo.CountryRepo
import com.tothferenc.airdb.repo.RunwayRepo

class QueryService(
    countryRepo: CountryRepo,
    airportRepo: AirportRepo,
    runwayRepo: RunwayRepo
) {

  def query(country: String): Iterable[(Airport, List[Runway])] = {
    for {
      country <- countryRepo.byCode(country).orElse(countryRepo.byName(country)).toList
      airport <- airportRepo.byCountryCode(country.code)
    } yield {
      airport -> runwayRepo.byAirportIdent(airport.ident)
    }
  }
}
