package com.tothferenc.airdb.repo.impl

import com.tothferenc.airdb.model.Runway
import com.tothferenc.airdb.parsing.Csv
import com.tothferenc.airdb.repo.RunwayRepo

class CsvBasedRunwayRepo(resourceUrl: String) extends RunwayRepo {

  private val runways = Csv.parseStream[Runway](getClass.getResourceAsStream(resourceUrl)).toVector

  private val runwaysByAirportIdent = runways.groupBy(_.airportIdent)

  override def byAirportIdent(airportIdent: String): Vector[Runway] =
    runwaysByAirportIdent.getOrElse(airportIdent, Vector.empty)

}