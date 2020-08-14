package com.avast.scala.hashes

import org.junit.runner.RunWith
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Sha256Test extends AnyFlatSpec with Matchers {
  it should "include the underlying bytes in equality comparison" in {
    Sha256("6a18b3c45107538de9d430f83a6af988edbddeb4e5a6bdb16f223a2fa37ee446") shouldBe Sha256("6a18b3c45107538de9d430f83a6af988edbddeb4e5a6bdb16f223a2fa37ee446")
  }

  it should "include the underlying bytes in hashcode computation" in {
    Sha256("6a18b3c45107538de9d430f83a6af988edbddeb4e5a6bdb16f223a2fa37ee446").hashCode() shouldBe Sha256("6a18b3c45107538de9d430f83a6af988edbddeb4e5a6bdb16f223a2fa37ee446").hashCode()
  }

  it should "convert the Sha256 to lower-case string" in {
    Sha256("6A18B3C45107538DE9D430F83A6AF988EDBDDEB4E5A6BDB16F223A2FA37EE446").toString() shouldBe "6a18b3c45107538de9d430f83a6af988edbddeb4e5a6bdb16f223a2fa37ee446"
  }
}
