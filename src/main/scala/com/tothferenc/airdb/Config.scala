package com.tothferenc.airdb

final case class Config(
  airportsUrl: String,
  countriesUrl: String,
  runwaysUrl: String
)

object Config {
  val test = Config("/airports.csv", "/countries.csv", "/runways.csv")
  val live = test
}
