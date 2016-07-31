package com.tothferenc.airdb.service
import com.tothferenc.airdb.model.Country

trait ReportService {

  def topBottomCountriesByAirportCount(top: Int, bottom: Int): (Vector[(Country, Int)], Vector[(Country, Int)])

  def runwayTypesByCountry: Vector[(Country, Vector[String])]
}
