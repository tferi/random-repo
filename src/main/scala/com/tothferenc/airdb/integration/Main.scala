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

    val Services(queryService, reportService) = Bootstrap.getServices(config)

    val in: InputStream = System.in
    val out: PrintStream = System.out

    val queryProcessor = new QueryProcessor(queryService, out)
    val reportProcessor = new ReportProcessor(reportService, out)

    val helptext: String = "Please type `report` or `query $country` to interact with the applicationor CTRL + D to exit."

    out.println(helptext)
    Using(Source.fromInputStream(in)) { reader =>
      reader.getLines().foreach {
        case query if query.startsWith("query ") =>
          queryProcessor.printQueryResult(query.stripPrefix("query"))
        case "report" =>
          reportProcessor.printTopBottomReport(reportService, out)
          reportProcessor.printRunwaysByCountry(reportService, out)
        case _ =>
          out.println(helptext)
      }
    }

    System.exit(0)
  }
}
