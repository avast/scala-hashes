package com.avast.scala.hashes

import org.json4s.CustomSerializer
import org.json4s.JsonAST.JString

package object json4s {
  implicit object Sha256Serializer extends CustomSerializer[Sha256](format => (
    { case JString(value) => Sha256(value) },
    { case fileId: Sha256 => JString(fileId.toString()) }
    ))

  implicit object MD5Serializer extends CustomSerializer[MD5](format => (
    { case JString(value) => MD5(value) },
    { case md5: MD5 => JString(md5.toString()) }
    ))

  implicit object Sha1Serializer extends CustomSerializer[Sha1](format => (
    { case JString(value) => Sha1(value) },
    { case sha1: Sha1 => JString(sha1.toString()) }
  ))
}
