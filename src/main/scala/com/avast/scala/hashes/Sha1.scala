package com.avast.scala.hashes

import io.circe.{Decoder, Encoder}

import java.util

case class Sha1(bytes: Array[Byte]) {
  require(bytes.length == 20, s"Invalid Sha1: ${Sha1.bytesLength} bytes expected but ${bytes.length} provided")

  final def toBase64String: String = bytes2base64(bytes)
  final def toHexString: String = bytes2hex(bytes)

  override def toString: String = bytes2hex(bytes)
  override def hashCode(): Int = util.Arrays.hashCode(bytes)
  override def equals(that: Any): Boolean = that match {
    case that: Sha1 => util.Arrays.equals(bytes, that.bytes)
    case _ => false
  }
}

object Sha1 {
  private val bytesLength = 20
  def apply(hexOrBase64: String): Sha1 = Sha1(tryHex2bytes(hexOrBase64, bytesLength).getOrElse(base642bytes(hexOrBase64)))

  implicit lazy val Sha1Decoder: Decoder[Sha1] = circe.prepareDecoder(Sha1(_))
  implicit lazy val Sha1Encoder: Encoder[Sha1] = Encoder.encodeString.contramap((s: Sha1) => s.toString)
}
