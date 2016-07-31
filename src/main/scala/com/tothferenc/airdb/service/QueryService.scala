package com.tothferenc.airdb.service
import com.tothferenc.airdb.model.Airport
import com.tothferenc.airdb.model.Runway

trait QueryService {

  def query(country: String): Iterable[(Airport, Vector[Runway])]
}
