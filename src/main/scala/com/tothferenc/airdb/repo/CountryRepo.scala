package com.tothferenc.airdb.repo

import com.tothferenc.airdb.model.Country
import com.tothferenc.airdb.parsing.Csv

class CountryRepo(resourceUrl: String) {

  private val countries = Csv.parseStream[Country](getClass.getResourceAsStream(resourceUrl)).toList

  private val countriesByUcName = countries.groupBy(_.name.toUpperCase())

  private val countriesByCode = countries.groupBy(_.code)

  def byName(name: String): Option[Country] =
    countriesByUcName.get(name.toUpperCase()).flatMap(_.headOption)

  def byCode(code: String): Option[Country] =
    countriesByCode.get(code.toUpperCase()).flatMap(_.headOption)
}
