package com.tothferenc.airdb.integration

import com.tothferenc.airdb.Config
import com.tothferenc.airdb.repo.CsvBasedAirportRepo
import com.tothferenc.airdb.repo.CsvBasedCountryRepo
import com.tothferenc.airdb.repo.CsvBasedRunwayRepo
import com.tothferenc.airdb.service.QueryService
import com.tothferenc.airdb.service.ReportService

object Bootstrap {

  def getServices(config: Config): Services = {
    val countryRepo = new CsvBasedCountryRepo(config.countriesUrl)
    val airportRepo = new CsvBasedAirportRepo(config.airportsUrl)
    val runwayRepo = new CsvBasedRunwayRepo(config.runwaysUrl)

    val queryService = new QueryService(
      countryRepo,
      airportRepo,
      runwayRepo
    )

    val reportService = new ReportService(
      countryRepo,
      airportRepo,
      runwayRepo
    )

    Services(queryService, reportService)
  }
}
