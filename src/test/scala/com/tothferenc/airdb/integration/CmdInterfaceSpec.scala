package com.tothferenc.airdb.integration

import java.io._
import java.nio.charset.StandardCharsets

import org.specs2.mutable.Specification

class CmdInterfaceSpec extends Specification {

  val welcomeMessage: String = CmdInterface.helptext + System.lineSeparator()

  "Cmd Interface" should {

    "print the help text on init" in {
      outputOf("") must startWith(CmdInterface.helptext)
    }

    "print the help text on invalid input" in {
      outputOf("invalid") must startWith(welcomeMessage + CmdInterface.helptext)
    }

    "have an expected country in the report" in {
      outputOf("report") must contain("Hungary")
    }

    "have the runways of an expected country in the report" in {
      outputOf("report") must contain("""Runways in United States: ASPH-G, GRVL, TURF, GRASS, GRAVEL, ASPH, TURF-G, TURF-F, MATS, CONC, TURF-P, CONC-G, GRAVEL-F, ASPH-TRTD, TURF-GRVL, WATER, ASPH-TURF, DIRT, GRVL-DIRT, DIRT-P, DIRT-TURF-G, PSP, CONC-TURF, DIRT-G, TURF-DIRT, DIRT-F, GRVL-G, ASPH-CONC-G, ASPH-P, WATER-E, CONC-E, TURF-GRVL-F, ROOF-TOP, ASPH-F, ASPH-E, GRVL-F, ASPH-DIRT, ASPH-TRTD-P, asphalt, TREATED, SAND, GRVL-TRTD-P, TURF-DIRT-F, TURF-GRVL-P, SOD, TURF-E, WOOD, ALUM, ASPH-TURF-P, GRVL-DIRT-P, TRTD-DIRT, ASPH-CONC, GRVL-P, GRAVEL-P, TURF-DIRT-P, GRVL-TRTD-F, ASPH-GRVL-P, ASPH-GRVL, WATER-G, CONC-F, GRASS / SOD, TURF-GRVL-G, DIRT-TURF, ASP, GRAVEL-G, GRVL-TRTD, TRTD, ASPH-TURF-F, BRICK, ROOFTOP, DIRT-TRTD, DECK, GRE, CONC-P, CONC-GRVL, MATS-G, ASPH-L, TURF-DIRT-G, ASPH-TURF-G, DIRT-GRVL, GRAVEL-E, DIRT-GRVL-F, TURF-SAND-F, GRVL-E, GRVL-TURF-F, GRVL-TURF, CALICHE, C, GRASS-F, GRVL-DIRT-F, ASPH-DIRT-G, ALUM-DECK, TREATED-F, OIL&CHIP-T-G, CONC-GRVL-G, ASPH-GRVL-F, CON, UNK, CONC-TURF-G, ALUMINUM, ASPH-CONC-F, ASPH-TURF-E, ASPH-TRTD-G, DIRT-TURF-F, TREATED-G, COP, METAL, Water, Grass, NSTD, STEEL, TRTD-DIRT-P, , TREATED-E, ASPH-CONC-P, ICE, DIRT-GRVL-P, GRVL-TURF-P, PFC, GVL, OILED DIRT, DIRT-SAND, STEEL-CONC, GRVL-DIRT-G, CONC-TURF-F, ASPH-GRVL-G, CONC-TRTD, GRVL-TURF-G, UNKNOWN, GRS, ASPH-TRTD-F, PEM, COM, PER, U, BIT, CONC-TRTD-G, Asphalt, ASPH-DIRT-P, PIERCED STEEL PLANKING / LANDING MATS / MEMBRANES, ASPH/ CONC, NATURAL SOIL, ASP/CONC, GRAVEL / CINDERS / CRUSHED ROCK / CORAL/SHELLS / SLAG, DIRT-GRVL-G, Natural Soil, CLA, NEOPRENE, Conc, TRTD-DIRT-F, DIRT-E, GRVL-DIRT-E, CORAL, COR, SAND-F, None, TURF-TRTD-G, turf, grass, ASPH/CONC, Turf, Gravel, concrete, LAT, ASPH/GRVL-F""")
    }

    "have an expected airport in the query" in {
      outputOf("query hu") must contain("Budapest Ferenc Liszt International Airport")
    }

    "not have an unexpected airport in the query" in {
      outputOf("query us") must not contain "Budapest Ferenc Liszt International Airport"
    }
  }

  def outputOf(input: String): String = {
    val in = strToInputStream(input)
    val out: ByteArrayOutputStream = new ByteArrayOutputStream()
    val print = new PrintStream(out, true)
    TestServices.cmdInterface.runWith(in, print)
    out.toString
  }

  def strToInputStream(input: String): ByteArrayInputStream = {
    new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))
  }
}
