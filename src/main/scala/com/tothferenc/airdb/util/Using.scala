package com.tothferenc.airdb.util

import scala.language.reflectiveCalls

object Using {
  def apply[Resource <: { def close(): Unit }, Result](resource: Resource)(use: Resource => Result): Result = {
    try {
      use(resource)
    } finally {
      resource.close()
    }
  }
}
