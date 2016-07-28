package com.tothferenc.airdb.service

import com.tothferenc.airdb.model.Country
import com.tothferenc.airdb.repo.AirportRepo
import com.tothferenc.airdb.repo.CountryRepo
import com.tothferenc.airdb.repo.RunwayRepo

class ReportService(
    countryRepo: CountryRepo,
    airportRepo: AirportRepo,
    runwayRepo: RunwayRepo
) {

  private val countryResolver = (withResolvedCountry _).tupled

  private def withResolvedCountry(countryCode: String, count: Int): Iterable[(Country, Int)] = {
    countryRepo.byCode(countryCode).map(_ -> count)
  }

  def topBottomCountriesByAirportCount(top: Int, bottom: Int): (Array[(Country, Int)], Array[(Country, Int)]) = {
    val airportCountByCountryAsc = airportRepo.countsByCountry.toArray.sortBy(_._2)
    val countryCount = airportCountByCountryAsc.length

    val lowest = airportCountByCountryAsc.take(top).flatMap(countryResolver)
    val highest = airportCountByCountryAsc.drop(countryCount - bottom).flatMap(countryResolver)
    highest -> lowest
  }

	def runwayTypesByCountry: List[(Country, List[String])] = {
		countryRepo.all.map { country =>
			val runwayTypes = airportRepo.byCountryCode(country.code).flatMap { airport =>
				runwayRepo.byAirportIdent(airport.ident).map(_.surface)
			}.distinct
			country -> runwayTypes
		}
	}
}
