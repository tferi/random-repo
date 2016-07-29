package com.tothferenc.airdb.parsing

import com.tothferenc.airdb.Config
import com.tothferenc.airdb.model.Airport
import com.tothferenc.airdb.model.Country
import com.tothferenc.airdb.model.Runway
import org.specs2.mutable.Specification

class ParsingSpec extends Specification {

  "country csv" should {
    "be parsed with an expected element present" in {
      val expected = Country("HU", "Hungary")
      val resourceUrl: String = Config.test.countriesUrl

      elementIsParsedFromResource(expected, resourceUrl)
    }
  }

  "airport csv" should {
    "be parsed with an expected element present" in {
      val expected = Airport("LHBP", "Budapest Ferenc Liszt International Airport", "HU")
      val resourceUrl: String = Config.test.airportsUrl

      elementIsParsedFromResource(expected, resourceUrl)
    }
  }

  "country csv" should {
    "be parsed with an expected element present" in {
      val expected = Runway("236449", "LHBP", "CON")
      val resourceUrl: String = Config.test.runwaysUrl

      elementIsParsedFromResource(expected, resourceUrl)
    }
  }

  private def elementIsParsedFromResource[T: CsvLineParser](expected: T, resourceUrl: String) = {
    val stream = getClass.getResourceAsStream(resourceUrl)
    val result = Csv.parseStream[T](stream)
    result.contains(expected) must beTrue
  }
}
