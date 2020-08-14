package com.avast.scala.hashes

import cats.syntax.all._
import io.circe._

package object circe {
  implicit val Sha256Decoder: Decoder[Sha256] = prepareDecoder(Sha256(_))
  implicit val Sha256Encoder: Encoder[Sha256] = Encoder.encodeString.contramap((s: Sha256) => s.toBase64String)
  implicit val MD5Decoder: Decoder[MD5] = prepareDecoder(MD5(_))
  implicit val MD5Encoder: Encoder[MD5] = Encoder.encodeString.contramap((h: MD5) => h.toBase64String)
  implicit val Sha1Decoder: Decoder[Sha1] = prepareDecoder(Sha1(_))
  implicit val Sha1Encoder: Encoder[Sha1] = Encoder.encodeString.contramap((s: Sha1) => s.toBase64String)

  private def prepareDecoder[A](parse: String => A): Decoder[A] =
    (c: HCursor) => {
      c.value.as[String].flatMap { s =>
        Either
          .catchNonFatal(parse(s))
          .leftMap { e =>
            DecodingFailure(e.toString, c.history)
          }
      }
    }

  object hex {
    implicit val Sha256Decoder: Decoder[Sha256] = prepareDecoder(Sha256(_))
    implicit val Sha256Encoder: Encoder[Sha256] = Encoder.encodeString.contramap((s: Sha256) => s.toHexString)
    implicit val MD5Decoder: Decoder[MD5] = prepareDecoder(MD5(_))
    implicit val MD5Encoder: Encoder[MD5] = Encoder.encodeString.contramap((h: MD5) => h.toHexString)
    implicit val Sha1Decoder: Decoder[Sha1] = prepareDecoder(Sha1(_))
    implicit val Sha1Encoder: Encoder[Sha1] = Encoder.encodeString.contramap((s: Sha1) => s.toHexString)
  }
}
