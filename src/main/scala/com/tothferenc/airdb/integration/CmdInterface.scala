package com.tothferenc.airdb.integration

import java.io.InputStream
import java.io.PrintStream

import com.tothferenc.airdb.util.Using

import scala.io.Source

object CmdInterface {
  val helptext: String = "Please type `report` or `query $country` to interact with the application or CTRL + D to exit."
}

class CmdInterface(queryProcessor: QueryProcessor, reportProcessor: ReportProcessor) {

  import CmdInterface._

  def runWith(implicit in: InputStream, out: PrintStream): Unit = {
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
