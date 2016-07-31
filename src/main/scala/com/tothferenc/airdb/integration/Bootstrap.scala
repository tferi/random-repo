package com.tothferenc.airdb.integration

import com.tothferenc.airdb.Config
import com.tothferenc.airdb.repo.impl.CsvBasedAirportRepo
import com.tothferenc.airdb.repo.impl.CsvBasedCountryRepo
import com.tothferenc.airdb.repo.impl.CsvBasedRunwayRepo
import com.tothferenc.airdb.service.impl.RepoBasedQueryService
import com.tothferenc.airdb.service.impl.RepoBasedReportService

object Bootstrap {

  def getServices(config: Config): Services = {
    val countryRepo = new CsvBasedCountryRepo(config.csvLocations.countriesUrl)
    val airportRepo = new CsvBasedAirportRepo(config.csvLocations.airportsUrl)
    val runwayRepo = new CsvBasedRunwayRepo(config.csvLocations.runwaysUrl)

    val queryService = new RepoBasedQueryService(
      countryRepo,
      airportRepo,
      runwayRepo
    )

    val reportService = new RepoBasedReportService(
      countryRepo,
      airportRepo,
      runwayRepo
    )

    Services(queryService, reportService)
  }
}
