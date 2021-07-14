package com.avast.scala.hashes

import org.junit.runner.RunWith
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PackageTest extends AnyFlatSpec with Matchers {

  Seq((Array(0, 25, 145, 255).map(_.toByte), Some("-"), "00-19-91-ff"),
      (Array.emptyByteArray, Some("-"), ""),
      (Array(0, 25, 145, 255).map(_.toByte), None, "001991ff")).foreach { case (bytes: Array[Byte], separator, expected) =>

    it must s"convert ${bytes.mkString("Array(", ", ", ")")} separated with $separator to $expected" in {
      bytes2hex(bytes, separator) shouldBe expected
    }

  }

  Seq(
    ("TRU", 3, None),
    ("", 1, None),
    (" TRU", 3, None),
    (" ", 0, Some(Array.emptyByteArray)),
    ("001991FF", 3, None),
    ("001991FF", 5, None),
    ("001991FF", 4, Some(Array(0, 25, 145, 255).map(_.toByte)))).foreach { case (input, expectedBytes, expectedResult) =>
    it must s"return expected result from tryHex2bytes for input '$input' string and expecting $expectedBytes bytes" in {
      tryHex2bytes(input, expectedBytes) match {
        case Some(value) => value shouldBe expectedResult.get
        case None => expectedResult shouldBe None
      }
    }
  }

  Seq(
    ("001991FF", Array(0, 25, 145, 255).map(_.toByte)),
    (" 001991FF ", Array(0, 25, 145, 255).map(_.toByte)),
    ("uu001991FFuu", Array(0, 25, 145, 255).map(_.toByte)),
    ("WFTF", Array(255).map(_.toByte)),
    ("uuuu", Array.emptyByteArray),
    ("", Array.emptyByteArray)).foreach { case (input, expectedResult) =>
    it must s"return expected result from hex2bytes for input '$input' string" in {
      hex2bytes(input) shouldBe expectedResult
    }
  }

}
