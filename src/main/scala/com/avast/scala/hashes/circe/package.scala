package com.avast.scala.hashes

import io.circe.{Decoder, Encoder}

package object circe {
  implicit val Sha256Decoder = Decoder.decodeString.map(Sha256(_))
  implicit val Sha256Encoder = Encoder.encodeString.contramap((s: Sha256) => s.toString)
  implicit val MD5Decoder = Decoder.decodeString.map(MD5(_))
  implicit val MD5Encoder = Encoder.encodeString.contramap((h: MD5) => h.toString)
  implicit val Sha1Decoder = Decoder.decodeString.map(Sha1(_))
  implicit val Sha1Encoder = Encoder.encodeString.contramap((s: Sha1) => s.toString)
}
