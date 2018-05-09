package com.avast.scala.hashes

import java.util

case class MD5(bytes: Array[Byte]) {
  require(bytes.length == 16, s"Invalid MD5: 16 bytes expected but ${bytes.length} provided")

  override def toString: String = bytes2hex(bytes)
  override def hashCode(): Int = util.Arrays.hashCode(bytes)
  override def equals(that: Any): Boolean = that match {
    case that: MD5 => util.Arrays.equals(bytes, that.bytes)
    case _ => false
  }
}

object MD5 {
  def apply(hex: String): MD5 = MD5(hex2bytes(hex))
}
