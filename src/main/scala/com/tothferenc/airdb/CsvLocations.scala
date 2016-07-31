package com.tothferenc.airdb

object Config {
  val test = Config(
    CsvLocations("/airports.csv", "/countries.csv", "/runways.csv"),
    ReportConfig(10, 10)
  )
  val live = test
}

final case class Config(
  csvLocations: CsvLocations,
  reportConfig: ReportConfig
)

final case class CsvLocations(
  airportsUrl: String,
  countriesUrl: String,
  runwaysUrl: String
)

final case class ReportConfig(
  topLimit: Int,
  bottomLimit: Int
)

