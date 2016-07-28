package com.tothferenc.airdb.integration

import java.io.PrintStream

import com.tothferenc.airdb.service.QueryService

class QueryProcessor(queryService: QueryService, out: PrintStream) {
  def printQueryResult(line: String): Unit = {
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
