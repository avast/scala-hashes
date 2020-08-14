package com.avast.scala.hashes

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
  def apply(hexOrBase64: String): Sha256 = Sha256(if (hexOrBase64.length == 2 * bytesLength) hex2bytes(hexOrBase64) else base642bytes(hexOrBase64))
}
