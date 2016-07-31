package com.tothferenc.airdb.service

import com.tothferenc.airdb.Config
import com.tothferenc.airdb.ReportConfig
import com.tothferenc.airdb.integration.TestServices
import org.specs2.mutable.Specification

class ReportServiceSpec extends Specification {

  val reportConfig: ReportConfig = Config.test.reportConfig
  "ReportService" should {
    "return the requested number of top/bottom items in the report" in {
      val (top, bottom) = TestServices.reportService.topBottomCountriesByAirportCount(reportConfig.topLimit, reportConfig.topLimit)
      top.length mustEqual reportConfig.topLimit
      bottom.length mustEqual reportConfig.bottomLimit
    }

    "return top/bottom items where the top item's value is not less than the bottom items'" in {
      val (top, bottom) = TestServices.reportService.topBottomCountriesByAirportCount(reportConfig.topLimit, reportConfig.topLimit)
      val max = top.maxBy(_._2)._2
      forall(bottom)(_._2 <= max)
    }
  }
}
