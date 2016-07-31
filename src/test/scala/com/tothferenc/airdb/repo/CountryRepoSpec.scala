package com.tothferenc.airdb.repo

import com.tothferenc.airdb.Config
import com.tothferenc.airdb.model.Country
import com.tothferenc.airdb.repo.impl.CsvBasedCountryRepo
import org.specs2.mutable.Specification

class CountryRepoSpec extends Specification {

  val repo = new CsvBasedCountryRepo(Config.test.csvLocations.countriesUrl)

  "byName" should {
    "yield the expected result if present" in {
      repo.byName("hUnGaRy") must beSome(Country("HU", "Hungary"))
    }
    "yield none if the country is not present" in {
      repo.byName("whatever") must beNone
    }
  }

  "byCode" should {
    "yield the expected result if present" in {
      repo.byCode("hu") must beSome(Country("HU", "Hungary"))
    }
    "yield none if the country is not present" in {
      repo.byName("whatever") must beNone
    }
  }
}
