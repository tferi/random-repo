package com.tothferenc.airdb.repo
import com.tothferenc.airdb.model.Runway

trait RunwayRepo {

  def byAirportIdent(airportIdent: String): Vector[Runway]
}
