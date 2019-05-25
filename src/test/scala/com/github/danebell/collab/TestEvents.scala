package com.github.danebell.collab

import TestUtils._
import org.scalatest.{FlatSpec, Matchers}

class TestEvents extends FlatSpec with Matchers {
  "CollabSystem" should "find proposals" in {
    val ms = system.extract("Councilmember Bradford made a motion to enter into Task Order No.17 with Sweitzer Engineering, Inc. for the necessary design phase documents, erosion control plans and permit applications necessary for the replacement of the aging sewage force main from the Shattuck Industrial Blvd Lift Station to the north end of the airport.")
    val less = ms filter (_ matches "Propose")
    less should not be empty
  }
}