package com.tothferenc.airdb.service.impl

import com.tothferenc.airdb.model.Country
import com.tothferenc.airdb.repo._
import com.tothferenc.airdb.service.ReportService

class RepoBasedReportService(
    countryRepo: CountryRepo,
    airportRepo: AirportRepo,
    runwayRepo: RunwayRepo
) extends ReportService {

  private val countryResolver = (withResolvedCountry _).tupled

  private def withResolvedCountry(countryCode: String, count: Int): Iterable[(Country, Int)] = {
    countryRepo.byCode(countryCode).map(_ -> count)
  }

  override def topBottomCountriesByAirportCount(top: Int, bottom: Int): (Vector[(Country, Int)], Vector[(Country, Int)]) = {
    val airportCountByCountryAsc = airportRepo.countsByCountry.toVector.sortBy(_._2)
    val countryCount = airportCountByCountryAsc.length

    val lowest = airportCountByCountryAsc.take(top).flatMap(countryResolver)
    val highest = airportCountByCountryAsc.drop(countryCount - bottom).flatMap(countryResolver)

    highest -> lowest
  }

  override def runwayTypesByCountry: Vector[(Country, Vector[String])] = {
    countryRepo.all.map { country =>
      val runwayTypes = airportRepo.byCountryCode(country.code).flatMap { airport =>
        runwayRepo.byAirportIdent(airport.ident).map(_.surface)
      }.distinct
      country -> runwayTypes
    }
  }
}
