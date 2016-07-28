package com.tothferenc.airdb.integration

import java.io._
import java.nio.charset.StandardCharsets

import org.specs2.mutable.Specification

class CmdInterfaceSpec extends Specification {

  val welcomeMessage: String = CmdInterface.helptext + System.lineSeparator()

  "Cmd Interface" should {

    "print the help text on init" in {
      executedWith("") must startWith(CmdInterface.helptext)
    }

    "print the help text on invalid input" in {
      executedWith("invalid") must startWith(welcomeMessage + CmdInterface.helptext)
    }

    "have an expected country in the report" in {
      executedWith("report") must contain("Hungary")
    }

    "have an expected airport in the query" in {
      executedWith("query hu") must contain("Budapest Ferenc Liszt International Airport")
    }
  }

  def executedWith(input: String): String = {
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
