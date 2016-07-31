package com.tothferenc.airdb.integration

import java.io._

import com.tothferenc.airdb.Config

object Main {

  def main(args: Array[String]) {
    val config = Config.live

    val Services(queryService, reportService) = Bootstrap.getServices(config)

    val in: InputStream = System.in
    val out: PrintStream = System.out

    val queryProcessor = new QueryProcessor(queryService)
    val reportProcessor = new ReportProcessor(reportService, config.reportConfig)

    val cmdInterface = new CmdInterface(queryProcessor, reportProcessor)

    cmdInterface.runWith(in, out)

    System.exit(0)
  }

}
