package com.tothferenc.airdb.integration

import java.io.InputStream
import java.io.PrintStream

import com.tothferenc.airdb.util.Using

import scala.io.Source

class CmdInterface(queryProcessor: QueryProcessor, reportProcessor: ReportProcessor) {

  def runWith(in: InputStream, out: PrintStream): Unit = {
    val helptext: String = "Please type `report` or `query $country` to interact with the applicationor CTRL + D to exit."
    out.println(helptext)
    Using(Source.fromInputStream(in)) { reader =>
      reader.getLines().foreach {
        case query if query.startsWith("query ") =>
          queryProcessor.printQueryResult(query.stripPrefix("query"))
        case "report" =>
          reportProcessor.printTopBottomReport()
          reportProcessor.printRunwaysByCountry()
        case _ =>
          out.println(helptext)
      }
    }
  }
}
