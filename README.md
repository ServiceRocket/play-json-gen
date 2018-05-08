Play Json ScalaCheckGenerators
==============================
[![Build Status](https://travis-ci.org/ServiceRocket/play-json-gen.svg?branch=master)](https://travis-ci.org/ServiceRocket/play-json-gen)

ScalaCheck generators for Play Json.
### Usage
```
libraryDependencies += "com.servicerocket" %% "play-json-gen" % version
libraryDependencies += "org.scalacheck" %% "scalacheck" % version
libraryDependencies += "com.typesafe.play" %% "play-json" % version
```
Make sure to add a compatible ScalaCheck and Play Json dependencies
explicitly, if not already in the scope of your project, as none will be
provided transitively (provided scope).

Use in your tests:
```scala
import com.servicerocket.play.json.JsonGen

JsonGen.jsValueGen
JsonGen.jsObjectGen
JsonGen.jsStringGen
```
### Compatibility Matrix
|  Play Json Gen | ScalaCheck     | Play Json | Scala           |
| -------------- | -------------- | --------- | --------------- |
| `0.1.0`        | `1.13.5`       | `2.15.18` | `2.11`          |
| `0.1.1`        | `1.13.5`       | `2.6.8`   | `2.12`          |
| `0.1.2`        | `1.13.5`       | `2.15.18` | `2.12` & `2.11` |