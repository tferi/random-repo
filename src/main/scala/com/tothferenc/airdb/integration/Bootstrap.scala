package com.tothferenc.airdb.integration

import com.tothferenc.airdb.Config
import com.tothferenc.airdb.repo.AirportRepo
import com.tothferenc.airdb.repo.CountryRepo
import com.tothferenc.airdb.repo.RunwayRepo
import com.tothferenc.airdb.service.QueryService
import com.tothferenc.airdb.service.ReportService

object Bootstrap {

  def getServices(config: Config): Services = {
    val countryRepo = new CountryRepo(config.countriesUrl)
    val airportRepo = new AirportRepo(config.airportsUrl)
    val runwayRepo = new RunwayRepo(config.runwaysUrl)

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
