package com.tothferenc.airdb.repo

import com.tothferenc.airdb.model.Runway
import com.tothferenc.airdb.parsing.Csv

class RunwayRepo(resourceUrl: String) {

  private val runways = Csv.parseStream[Runway](getClass.getResourceAsStream(resourceUrl)).toVector

  private val runwaysByAirportIdent = runways.groupBy(_.airportIdent)

  def byAirportIdent(airportIdent: String): Vector[Runway] =
    runwaysByAirportIdent.getOrElse(airportIdent, Vector.empty)

}