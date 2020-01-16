package com.avast.scala.hashes.circe

import com.avast.scala.hashes.Sha256
import io.circe.{DecodingFailure, Json}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class Sha256DecoderTest extends FlatSpec with Matchers {
  "it" should "deserialize valid SHA256" in {
    val value = Sha256("6a18b3c45107538de9d430f83a6af988edbddeb4e5a6bdb16f223a2fa37ee446")

    val result = Sha256Decoder.decodeJson(Json.fromString(value.toString))

    assertResult(Right(value))(result)
  }

  "deserialization" should "correctly fail in case of invalid Sha256" in {
    val result = Sha256Decoder.decodeJson(Json.fromString("InvalidValue"))

    result match {
      case Left(_: DecodingFailure) => succeed
      case _ => fail()
    }
  }
}
