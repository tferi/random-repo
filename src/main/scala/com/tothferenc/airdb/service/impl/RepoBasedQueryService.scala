package com.tothferenc.airdb.service.impl

import com.tothferenc.airdb.model.Airport
import com.tothferenc.airdb.model.Runway
import com.tothferenc.airdb.repo._
import com.tothferenc.airdb.service.QueryService

class RepoBasedQueryService(
    countryRepo: CountryRepo,
    airportRepo: AirportRepo,
    runwayRepo: RunwayRepo
) extends QueryService {

  override def query(country: String): Iterable[(Airport, Vector[Runway])] = {
    for {
      country <- countryRepo.byCode(country).orElse(countryRepo.byName(country)).toVector
      airport <- airportRepo.byCountryCode(country.code)
    } yield {
      airport -> runwayRepo.byAirportIdent(airport.ident)
    }
  }
}
