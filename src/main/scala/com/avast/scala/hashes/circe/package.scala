package com.avast.scala.hashes

import io.circe._

import scala.reflect.ClassTag
import scala.util.control.NonFatal

package object circe {
  implicit val Sha256Decoder: Decoder[Sha256] = prepareDecoder(Sha256(_))
  implicit val Sha256Encoder: Encoder[Sha256] = Encoder.encodeString.contramap((s: Sha256) => s.toString)
  implicit val MD5Decoder: Decoder[MD5] = prepareDecoder(MD5(_))
  implicit val MD5Encoder: Encoder[MD5] = Encoder.encodeString.contramap((h: MD5) => h.toString)
  implicit val Sha1Decoder: Decoder[Sha1] = prepareDecoder(Sha1(_))
  implicit val Sha1Encoder: Encoder[Sha1] = Encoder.encodeString.contramap((s: Sha1) => s.toString)

  private def prepareDecoder[A: ClassTag](parse: String => A): Decoder[A] =
    (c: HCursor) => {
      c.value.as[String].flatMap { s =>
        try {
          Right(parse(s))
        } catch {
          case NonFatal(_) =>
            Left {
              DecodingFailure(
                implicitly[ClassTag[A]].runtimeClass.getSimpleName,
                c.history)
            }
        }
      }
    }
}
