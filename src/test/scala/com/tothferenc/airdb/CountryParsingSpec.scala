package com.tothferenc.airdb

import com.tothferenc.airdb.model.Country
import com.tothferenc.airdb.parsing.Csv
import org.specs2.mutable.Specification

class CountryParsingSpec extends Specification {

  "country csv" should {
    "be parsed with an expected element present" in {
      val expected = Country("HU", "Hungary")
      val stream = getClass.getResourceAsStream("/countries.csv")
      val result = Csv.parseStream[Country](stream)
      result.contains(expected) must beTrue
    }
  }

}
