package com.tothferenc.airdb.repo

import com.tothferenc.airdb.model.Runway
import com.tothferenc.airdb.parsing.Csv

class RunwayRepo(resourceUrl: String) {

  private val runways = Csv.parseStream[Runway](getClass.getResourceAsStream(resourceUrl)).toList

  private val runwaysByAirportIdent = runways.groupBy(_.airportIdent)

  def byAirportIdent(airportIdent: String): List[Runway] =
    runwaysByAirportIdent.getOrElse(airportIdent, Nil)

}