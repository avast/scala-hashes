package com.avast.scala.hashes

import java.util

case class Sha1(bytes: Array[Byte]) {
  require(bytes.length == 20, s"Invalid Sha1: 20 bytes expected but ${bytes.length} provided")

  override def toString: String = bytes2hex(bytes)
  override def hashCode(): Int = util.Arrays.hashCode(bytes)
  override def equals(that: Any): Boolean = that match {
    case that: Sha1 => util.Arrays.equals(bytes, that.bytes)
    case _ => false
  }
}

object Sha1 {
  def apply(hex: String): Sha1 = Sha1(hex2bytes(hex))
}
