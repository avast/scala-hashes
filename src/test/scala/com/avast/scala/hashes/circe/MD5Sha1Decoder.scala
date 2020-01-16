package com.avast.scala.hashes.circe

import com.avast.scala.hashes.MD5
import io.circe.{DecodingFailure, Json}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class MD5Sha1Decoder extends FlatSpec with Matchers {
  "it" should "deserialize valid MD5" in {
    val value = MD5("6a18b3c45107538de9d430f83a6af988")

    val result = MD5Decoder.decodeJson(Json.fromString(value.toString))

    assertResult(Right(value))(result)
  }

  "deserialization" should "correctly fail in case of invalid MD5" in {
    val result = MD5Decoder.decodeJson(Json.fromString("InvalidValue"))

    result match {
      case Left(_: DecodingFailure) => succeed
      case _ => fail()
    }
  }
}
