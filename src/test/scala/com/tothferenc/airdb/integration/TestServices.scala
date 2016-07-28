package com.tothferenc.airdb.integration

import com.tothferenc.airdb.Config

object TestServices {
  val Services(queryService, reportService) = Bootstrap.getServices(Config.test)
  val cmdInterface = new CmdInterface(new QueryProcessor(queryService), new ReportProcessor(reportService))
}
