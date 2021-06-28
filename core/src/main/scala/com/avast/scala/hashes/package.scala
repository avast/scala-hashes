package com.avast.scala

import java.util.Base64
import java.util.regex.Pattern

package object hashes {

  private val hexAllowedCharactersRegex = Pattern.compile("[^0-9A-Fa-f]")
  def hex2bytes(hex: String): Array[Byte] = hexbytesUnchecked(hexAllowedCharactersRegex.matcher(hex).replaceAll(""))
  private def hexbytesUnchecked(hex: String): Array[Byte] = hex.sliding(2, 2).toArray.map(Integer.parseInt(_, 16).toByte)
  def tryHex2bytes(maybeHex: String, expectedBytes: Int): Option[Array[Byte]] = {
    val clean = hexAllowedCharactersRegex.matcher(maybeHex).replaceAll("")
    if (clean.length == 2 * expectedBytes) Some(hexbytesUnchecked(clean)) else None
  }

  def bytes2hex(bytes: Array[Byte], sep: Option[String] = None): String =
    sep match {
      case None => bytes.map("%02x".format(_)).mkString
      case _ => bytes.map("%02x".format(_)).mkString(sep.get)
    }

  def base642bytes(base64: String): Array[Byte] = Base64.getDecoder.decode(base64)

  def bytes2base64(bytes: Array[Byte]): String = Base64.getEncoder.encodeToString(bytes)
}
