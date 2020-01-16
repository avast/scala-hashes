package com.avast.scala.hashes.circe

import com.avast.scala.hashes.{Sha1, Sha256}
import io.circe.{DecodingFailure, Json}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class Sha1DecoderTest extends FlatSpec with Matchers {
  "it" should "deserialize valid Sha1" in {
    val value = Sha1("0fd08a268f6032ce2a83a17ac8adceaf82ade5e3")

    val result = Sha1Decoder.decodeJson(Json.fromString(value.toString))

    assertResult(Right(value))(result)
  }

  "deserialization" should "correctly fail in case of invalid Sha1" in {
    val result = Sha1Decoder.decodeJson(Json.fromString("InvalidValue"))

    result match {
      case Left(_: DecodingFailure) => succeed
      case _ => fail()
    }
  }
}