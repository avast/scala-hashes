package com.avast.scala.hashes

import cats.syntax.all._
import io.circe._

package object circe {
  // links here to stay backwards compatible, but users should use de-/en-coders from the companion objects, i.e. out of the box without imports
  implicit lazy val Sha256Decoder: Decoder[Sha256] = Sha256.Sha256Decoder
  implicit lazy val Sha256Encoder: Encoder[Sha256] = Sha256.Sha256Encoder
  implicit lazy val MD5Decoder: Decoder[MD5] = MD5.MD5Decoder
  implicit lazy val MD5Encoder: Encoder[MD5] = MD5.MD5Encoder
  implicit lazy val Sha1Decoder: Decoder[Sha1] = Sha1.Sha1Decoder
  implicit lazy val Sha1Encoder: Encoder[Sha1] = Sha1.Sha1Encoder

  private[hashes] def prepareDecoder[A](parse: String => A): Decoder[A] =
    (c: HCursor) => {
      c.value.as[String].flatMap { s =>
        Either
          .catchNonFatal(parse(s))
          .leftMap { e =>
            DecodingFailure(e.toString, c.history)
          }
      }
    }
}
