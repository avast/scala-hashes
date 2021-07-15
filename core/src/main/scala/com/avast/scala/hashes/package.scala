package com.avast.scala

import java.util.Base64

package object hashes {

  /**
   * Performs a check if the input string has the right count of characters to be a valid HEX string for expectedBytes bytes.
   * If so, decodes them, returns None otherwise.
   */
  def tryHex2bytes(maybeHex: String, expectedBytes: Int): Option[Array[Byte]] = {
    if (maybeHex.length == 2 * expectedBytes) Some(hex2bytes(maybeHex)) else None
  }

  /**
   * Transform the input string to a new byte array, supposing all the characters as valid HEX characters (both lower and upper case are accepted).
   */
  def hex2bytes(hex: String): Array[Byte] = {
    val r = new Array[Byte](hex.length / 2)
    var i = 0
    while (i < (hex.length / 2)) {
      r(i) = ((hexCharToInt(hex(i*2)) << 4) | hexCharToInt(hex(i*2+1))).toByte
      i += 1
    }
    r
  }

  private def hexCharToInt(c: Character): Int = c match {
    case c if c >= '0' && c <= '9' => c - '0'
    case c if c >= 'a' && c <= 'f' => 10 + (c - 'a')
    case c if c >= 'A' && c <= 'F' => 10 + (c - 'A')
    case _                         => throw new IllegalArgumentException
  }

  /**
   * Encodes bytes to lower-case HEX string, optionally with a separator between bytes (so between every two characters).
   */
  def bytes2hex(bytes: Array[Byte], sep: Option[String] = None): String =
    sep match {
      case None =>
        val sb = new StringBuilder(bytes.length * 2)
        bytes.foreach(byteToHexChars(_, sb))
        sb.toString()
      case Some(s) =>
        val sb = new StringBuilder(bytes.length * (2 + s.length))
        bytes.foreach { b =>
          byteToHexChars(b, sb)
          sb.append(s)
        }
        if (sb.isEmpty) "" else sb.substring(0, sb.length - s.length)
    }

  private val hexAlphabet = Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
  private def byteToHexChars(b: Byte, sb: StringBuilder): Unit = {
    sb.append(hexAlphabet((b >> 4 & 0x0f).toByte.toInt))
      .append(hexAlphabet((b & 0x0f).toByte.toInt))
  }

  def base642bytes(base64: String): Array[Byte] = Base64.getDecoder.decode(base64)

  def bytes2base64(bytes: Array[Byte]): String = Base64.getEncoder.encodeToString(bytes)
}
