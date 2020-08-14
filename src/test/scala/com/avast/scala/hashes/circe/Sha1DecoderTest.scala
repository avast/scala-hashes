package com.avast.scala.hashes.circe

import com.avast.scala.hashes.Sha1
import io.circe.{DecodingFailure, Json}
import org.junit.runner.RunWith
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Sha1DecoderTest extends AnyFlatSpec with Matchers {
  it should "deserialize valid Sha1" in {
    val value = Sha1("0fd08a268f6032ce2a83a17ac8adceaf82ade5e3")
    Sha1Decoder.decodeJson(Json.fromString(value.toHexString)) shouldBe Right(value)
    Sha1Decoder.decodeJson(Json.fromString(value.toBase64String)) shouldBe Right(value)
  }

  "deserialization" should "fail correctly in case of invalid Sha1" in {
    val result = Sha1Decoder.decodeJson(Json.fromString("InvalidValue"))
    result match {
      case Left(_: DecodingFailure) => succeed
      case _ => fail()
    }
  }
}