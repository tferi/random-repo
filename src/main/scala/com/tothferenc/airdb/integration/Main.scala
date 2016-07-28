package com.tothferenc.airdb.integration

import java.io._

import com.tothferenc.airdb.Config
import com.tothferenc.airdb.repo.AirportRepo
import com.tothferenc.airdb.repo.CountryRepo
import com.tothferenc.airdb.repo.RunwayRepo
import com.tothferenc.airdb.service.QueryService
import com.tothferenc.airdb.util.Using

import scala.io.Source

object Main {

  def main(args: Array[String]) {
    val config = Config.live

    val countryRepo = new CountryRepo(config.countriesUrl)
    val airportRepo = new AirportRepo(config.airportsUrl)
    val runwayRepo = new RunwayRepo(config.runwaysUrl)

    val service = new QueryService(
      countryRepo,
      airportRepo,
      runwayRepo
    )

    val inputStream: InputStream = System.in
    val printStream: PrintStream = System.out

    processQueries(service, inputStream, printStream)
  }

  private def processQueries(queryService: QueryService, inputStream: InputStream, printStream: PrintStream): Unit = {
    Using(Source.fromInputStream(inputStream)) { reader =>
      reader.getLines().foreach { line =>
        val result = queryService.query(line.trim)
        result.foreach {
          case (airport, runways) =>
            printStream.println(s"${airport.ident}: ${airport.name}")
            runways.foreach { runway =>
              printStream.println(s"\t${runway.id}: ${runway.surface}")
            }
        }
      }
    }
  }
}
