package com.tothferenc.airdb.repo
import com.tothferenc.airdb.model.Country

trait CountryRepo {

  def byName(name: String): Option[Country]

  def byCode(code: String): Option[Country]

  def all: Vector[Country]
}
