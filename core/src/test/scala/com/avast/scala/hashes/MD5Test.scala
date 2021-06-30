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

  it should "convert MD5 to lower-case" in {
    MD5("6A18B3C45107538DE9D430F83A6AF988").toString() shouldBe "6a18b3c45107538de9d430f83a6af988"
  }

  it should "convert hex value with additional characters" in {
    MD5("\"6A18B3C45107538DE9D430F83A6AF988 \"").toString() shouldBe "6a18b3c45107538de9d430f83a6af988"
  }
}
