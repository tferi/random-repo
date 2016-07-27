package com.tothferenc.airdb

import com.tothferenc.airdb.model.Airport
import com.tothferenc.airdb.model.Country
import com.tothferenc.airdb.model.Runway
import com.tothferenc.airdb.parsing.Csv
import com.tothferenc.airdb.parsing.CsvLineParser
import org.specs2.mutable.Specification

class ParsingSpec extends Specification {

  "country csv" should {
    "be parsed with an expected element present" in {
      val expected = Country("HU", "Hungary")
      val resourceUrl: String = "/countries.csv"

      elementIsParsedFromResource(expected, resourceUrl)
    }
  }

  "airport csv" should {
    "be parsed with an expected element present" in {
      val expected = Airport("LHBP", "Budapest Ferenc Liszt International Airport", "HU")
      val resourceUrl: String = "/airports.csv"

      elementIsParsedFromResource(expected, resourceUrl)
    }
  }

  "country csv" should {
    "be parsed with an expected element present" in {
      val expected = Runway("236449", "LHBP", "CON")
      val resourceUrl: String = "/runways.csv"

      elementIsParsedFromResource(expected, resourceUrl)
    }
  }

  private def elementIsParsedFromResource[T: CsvLineParser](expected: T, resourceUrl: String) = {
    val stream = getClass.getResourceAsStream(resourceUrl)
    val result = Csv.parseStream[T](stream)
    result.contains(expected) must beTrue
  }
}
