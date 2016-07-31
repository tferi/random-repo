package com.tothferenc.airdb.repo
import com.tothferenc.airdb.model.Airport

trait AirportRepo {

  def byCountryCode(countryCode: String): Vector[Airport]

  def countsByCountry: Map[String, Int]
}
