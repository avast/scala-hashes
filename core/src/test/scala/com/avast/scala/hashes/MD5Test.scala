package com.avast.scala.hashes

import org.junit.runner.RunWith
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MD5Test extends AnyFlatSpec with Matchers {
  it should "include the underlying bytes in equality comparison" in {
    MD5("6a18b3c45107538de9d430f83a6af988") shouldBe MD5("6a18b3c45107538de9d430f83a6aF988")
  }

  it should "include the underlying bytes in hashcode computation" in {
    MD5("6a18b3c45107538de9d430f83a6af988").hashCode() shouldBe MD5("6a18b3c45107538de9d430f83a6af988").hashCode()
  }

  it should "parse from base64" in {
    MD5("ahizxFEHU43p1DD4Omr5iA==").toHexString shouldBe "6a18b3c45107538de9d430f83a6af988"
  }
}
