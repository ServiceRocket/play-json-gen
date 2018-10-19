javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")
scalacOptions ++= Seq("-feature", "-target:jvm-1.8")
scalaVersion := "2.12.7"
val ScalacheckVersion = "1.14.0"
val PlayJsonVersion = "2.6.10"
val ScalaTestVersion = "3.0.5"

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
publishMavenStyle := true
publishTo := version { (v: String) =>
  val Nexus = "https://t-nx2.performancerocket.com/"
  if (v.trim.endsWith("SNAPSHOT")) Some("ServiceRocket's Snapshots" at Nexus + "content/repositories/snapshots")
  else Some("ServiceRocket's Releases" at Nexus + "content/repositories/releases")
}.value

name := "play-json-gen"
version := "0.1.2-SNAPSHOT"
description := "Play Json ScalaCheck Generators"
organization := "com.servicerocket"
organizationName := "ServiceRocket"
organizationHomepage := Option(url("http://www.servicerocket.com"))
libraryDependencies += "org.scalacheck" %% "scalacheck" % ScalacheckVersion % Provided
libraryDependencies += "com.typesafe.play" %% "play-json" % PlayJsonVersion % Provided
libraryDependencies += "org.scalatest" %% "scalatest" % ScalaTestVersion % Test