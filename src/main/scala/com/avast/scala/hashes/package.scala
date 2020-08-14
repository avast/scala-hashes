package com.avast.scala

import java.util.Base64

package object hashes {
  def hex2bytes(hex: String): Array[Byte] = hex.replaceAll("[^0-9A-Fa-f]", "").sliding(2, 2).toArray.map(Integer.parseInt(_, 16).toByte)

  def bytes2hex(bytes: Array[Byte], sep: Option[String] = None): String =
    sep match {
      case None => bytes.map("%02x".format(_)).mkString
      case _ => bytes.map("%02x".format(_)).mkString(sep.get)
    }

  def base642bytes(base64: String): Array[Byte] = Base64.getDecoder.decode(base64)

  def bytes2base64(bytes: Array[Byte]): String = Base64.getEncoder.encodeToString(bytes)
}
