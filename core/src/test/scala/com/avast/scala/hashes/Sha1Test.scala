package com.avast.scala.hashes

import org.junit.runner.RunWith
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Sha1Test extends AnyFlatSpec with Matchers {
  it should "include the underlying bytes in equality comparison" in {
    Sha1("0fd08a268f6032ce2a83a17ac8adceaf82ade5e3") shouldBe Sha1("0fd08a268f6032ce2a83a17ac8adceaf82ade5e3")
  }

  it should "include the underlying bytes in hashcode computation" in {
    Sha1("0fd08a268f6032ce2a83a17ac8adceaf82ade5e3").hashCode() shouldBe Sha1("0fd08a268f6032ce2a83a17ac8adceaf82ade5e3").hashCode()
  }

  it should "convert the Sha1 to lower-case string" in {
    Sha1("0FD08A268F6032CE2A83A17AC8ADCEAF82ADE5E3").toString() shouldBe "0fd08a268f6032ce2a83a17ac8adceaf82ade5e3"
  }

  it should "convert hex value with additional characters" in {
    Sha1("\"0FD08A268F6032CE2A83A17AC8ADCEAF82ADE5E3 \"").toString() shouldBe "0fd08a268f6032ce2a83a17ac8adceaf82ade5e3"
  }
}