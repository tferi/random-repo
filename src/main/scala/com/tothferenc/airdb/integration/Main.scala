package com.tothferenc.airdb.integration

import java.io._

import com.tothferenc.airdb.Config
import com.tothferenc.airdb.model.Country
import com.tothferenc.airdb.repo.AirportRepo
import com.tothferenc.airdb.repo.CountryRepo
import com.tothferenc.airdb.repo.RunwayRepo
import com.tothferenc.airdb.service.QueryService
import com.tothferenc.airdb.service.ReportService
import com.tothferenc.airdb.util.Using

import scala.io.Source

object Main {

  def main(args: Array[String]) {
    val config = Config.live

    val countryRepo = new CountryRepo(config.countriesUrl)
    val airportRepo = new AirportRepo(config.airportsUrl)
    val runwayRepo = new RunwayRepo(config.runwaysUrl)

    val queryService = new QueryService(
      countryRepo,
      airportRepo,
      runwayRepo
    )

    val reportService = new ReportService(
      countryRepo,
      airportRepo,
      runwayRepo
    )

    val inputStream: InputStream = System.in
    val printStream: PrintStream = System.out

    processQueries(queryService, inputStream, printStream)
    printTopBottomReport(reportService, printStream)
	  printRunwaysByCount(reportService, printStream)
  }

  private def printTopBottomReport(reportService: ReportService, out: PrintStream): Unit = {

    def printCountry(country: Country, count: Int): Unit = {
      out.println(s"${country.name} with $count airports.")
    }

    val (top, bottom) = reportService.topBottomCountriesByAirportCount(10, 10)
    val print = (printCountry _).tupled

    out.println("Top countries:")
    top.foreach(print)

    out.println("Bottom countries:")
    bottom.foreach(print)
  }

	private def printRunwaysByCount(reportService: ReportService, out: PrintStream): Unit = {
		reportService.runwayTypesByCountry.foreach {
			case (country, runways) => out.println(s"Runways in ${country.name}: ${runways.mkString(", ")}")
		}
	}

  private def processQueries(queryService: QueryService, in: InputStream, out: PrintStream): Unit = {
    Using(Source.fromInputStream(in)) { reader =>
      reader.getLines().foreach { line =>
        val result = queryService.query(line.trim)
        result.foreach {
          case (airport, runways) =>
            out.println(s"${airport.ident}: ${airport.name}")
            runways.foreach { runway =>
              out.println(s"\t${runway.id}: ${runway.surface}")
            }
        }
      }
    }
  }
}
