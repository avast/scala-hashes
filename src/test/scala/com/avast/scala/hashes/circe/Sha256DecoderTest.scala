package com.avast.scala.hashes.circe

import com.avast.scala.hashes.Sha256
import io.circe.{DecodingFailure, Json}
import org.junit.runner.RunWith
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Sha256DecoderTest extends AnyFlatSpec with Matchers {
  it should "deserialize valid SHA256" in {
    val value = Sha256("6a18b3c45107538de9d430f83a6af988edbddeb4e5a6bdb16f223a2fa37ee446")
    Sha256Decoder.decodeJson(Json.fromString(value.toHexString)) shouldBe Right(value)
    Sha256Decoder.decodeJson(Json.fromString(value.toBase64String)) shouldBe Right(value)
  }

  "deserialization" should "fail correctly in case of invalid Sha256" in {
    val result = Sha256Decoder.decodeJson(Json.fromString("InvalidValue"))
    result match {
      case Left(_: DecodingFailure) => succeed
      case _ => fail()
    }
  }
}
