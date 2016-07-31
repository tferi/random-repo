package com.tothferenc.airdb.repo

import com.tothferenc.airdb.model.Country
import com.tothferenc.airdb.parsing.Csv

class CsvBasedCountryRepo(resourceUrl: String) extends CountryRepo {

  private val countries = Csv.parseStream[Country](getClass.getResourceAsStream(resourceUrl)).toVector

  private val countriesByUcName = countries.groupBy(_.name.toUpperCase())

  private val countriesByCode = countries.groupBy(_.code)

  override def byName(name: String): Option[Country] =
    countriesByUcName.get(name.toUpperCase()).flatMap(_.headOption)

  override def byCode(code: String): Option[Country] =
    countriesByCode.get(code.toUpperCase()).flatMap(_.headOption)

  override def all: Vector[Country] = countries
}
