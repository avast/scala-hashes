package com.avast.scala.hashes

import io.circe.{Decoder, Encoder}

import java.util

case class MD5(bytes: Array[Byte]) {
  require(bytes.length == 16, s"Invalid MD5: ${MD5.bytesLength} bytes expected but ${bytes.length} provided")

  final def toBase64String: String = bytes2base64(bytes)
  final def toHexString: String = bytes2hex(bytes)

  override def toString: String = bytes2hex(bytes)
  override def hashCode(): Int = util.Arrays.hashCode(bytes)
  override def equals(that: Any): Boolean = that match {
    case that: MD5 => util.Arrays.equals(bytes, that.bytes)
    case _ => false
  }
}

object MD5 {
  private val bytesLength = 16
  def apply(hexOrBase64: String): MD5 = MD5(tryHex2bytes(hexOrBase64, bytesLength).getOrElse(base642bytes(hexOrBase64)))

  implicit lazy val MD5Decoder: Decoder[MD5] = circe.prepareDecoder(MD5(_))
  implicit lazy val MD5Encoder: Encoder[MD5] = Encoder.encodeString.contramap((h: MD5) => h.toString)
}
