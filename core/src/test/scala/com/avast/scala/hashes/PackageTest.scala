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
}
