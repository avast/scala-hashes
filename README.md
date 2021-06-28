# Scala Hashes [![Build](https://github.com/avast/scala-hashes/actions/workflows/build.yml/badge.svg)](https://github.com/avast/scala-hashes/actions/workflows/build.yml) [![Version](https://badgen.net/maven/v/maven-central/com.avast.hashes/scala-hashes_2.13)](https://repo1.maven.org/maven2/com/avast/hashes/scala-hashes_2.13/)

Case-classes representing MD5, SHA1, and SHA256 hashes.

```gradle
compile "com.avast.hashes:scala-hashes_2.13:$versionHere"
```

There are also decoders and encoders for [circe](https://github.com/travisbrown/circe) JSON library:
```gradle
compile "com.avast.hashes:scala-hashes-circe_2.13:$versionHere"
```