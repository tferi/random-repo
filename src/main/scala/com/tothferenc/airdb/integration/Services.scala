package com.tothferenc.airdb.integration

import com.tothferenc.airdb.service.QueryService
import com.tothferenc.airdb.service.ReportService

final case class Services(
  query: QueryService,
  report: ReportService
)