package com.avast.scala.hashes

import io.circe.{Decoder, Encoder}

import java.util

case class Sha256(bytes: Array[Byte]) {
  require(bytes.length == 32, s"Invalid Sha256: ${Sha256.bytesLength} bytes expected but ${bytes.length} provided")

  final def toBase64String: String = bytes2base64(bytes)
  final def toHexString: String = bytes2hex(bytes)

  override def toString: String = bytes2hex(bytes)
  override def hashCode(): Int = util.Arrays.hashCode(bytes)
  override def equals(that: Any): Boolean = that match {
    case that: Sha256 => util.Arrays.equals(bytes, that.bytes)
    case _ => false
  }
}

object Sha256 {
  private val bytesLength = 32
  def apply(hexOrBase64: String): Sha256 = Sha256(tryHex2bytes(hexOrBase64, bytesLength).getOrElse(base642bytes(hexOrBase64)))

  implicit lazy val Sha256Decoder: Decoder[Sha256] = circe.prepareDecoder(Sha256(_))
  implicit lazy val Sha256Encoder: Encoder[Sha256] = Encoder.encodeString.contramap((s: Sha256) => s.toString)
}
