package com.tothferenc.airdb.integration

import java.io.PrintStream

import com.tothferenc.airdb.model.Country
import com.tothferenc.airdb.service.ReportService

class ReportProcessor(reportService: ReportService) {

  def printReports()(implicit out: PrintStream): Unit = {
    printTopBottomReport()
    printRunwaysByCountry()
  }

  def printTopBottomReport()(implicit out: PrintStream): Unit = {

    def printCountry(country: Country, count: Int): Unit = {
      out.println(s"${country.name} with $count airports.")
    }

    val (top, bottom) = reportService.topBottomCountriesByAirportCount(10, 10)
    val print = (printCountry _).tupled

    out.println("Top countries:")
    top.foreach(print)
    out.println()

    out.println("Bottom countries:")
    bottom.foreach(print)
    out.println()
  }

  def printRunwaysByCountry()(implicit out: PrintStream): Unit = {
    out.println("Runway types by country")
    reportService.runwayTypesByCountry.foreach {
      case (country, runways) => out.println(s"Runways in ${country.name}: ${runways.mkString(", ")}")
    }
  }
}
