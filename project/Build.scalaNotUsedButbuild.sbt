//import sbt._
//import Keys._
//import play.Project._
//
//object ApplicationBuild extends Build {
//
//  val appName = "ewallethub"
//  val appVersion = "1.0-SNAPSHOT"
//
//  val appDependencies = Seq(
//    // Select Play modules
//    jdbc,      // The JDBC connection pool and the play.api.db API
//    //anorm,     // Scala RDBMS Library
//    //javaJdbc,  // Java database API
//    //javaEbean, // Java Ebean plugin
//    //javaJpa,   // Java JPA plugin
//    //filters,   // A set of built-in filters
//    javaCore, // The core Java API
//    // WebJars pull in client-side web libraries
//    "org.mongodb" %% "casbah" % "2.6.1",
//    "org.webjars" % "webjars-play" % "2.1.0",
//    "org.webjars" % "bootstrap" % "2.3.1",
//    "org.mockito" % "mockito-all" % "1.9.0" % "test",
//    //    "org.scala-lang.modules" %% "scala-async" % "0.9.0-M6",
//    //    "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",
//    //"org.scalamock" %% "scalamock-scalatest-support" % "latest.integration",
//    "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test",
//    //"org.scalamock" %% "scalamock-specs2-support" % "3.0.1" % "test",
//    //"org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test",
//    "com.netflix.rxjava" % "rxjava-scala" % "0.16.0",
//    "com.netflix.rxjava" % "rxjava-async-util" % "0.16.0",
//    "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
//    "com.typesafe.slick" %% "slick" % "1.0.1",
//    "com.typesafe.play" %% "play-slick" % "0.5.0.8",
//    "com.typesafe.akka" %% "akka-actor" % "2.1.0" % "test"
//      excludeAll (
//        ExclusionRule(organization = "akka.actor.Scheduler")),
//    "com.typesafe.akka" %% "akka-testkit" % "2.1.0" % "test"
//      excludeAll (
//        ExclusionRule(organization = "akka.actor.Scheduler")),
//    "org.scalacheck" %% "scalacheck" % "1.10.1" % "test" // Add your own project dependencies in the form:
//    // "group" % "artifact" % "version"
//    )
//
//  resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
//
//  libraryDependencies +=
//    "log4j" % "log4j" % "1.2.15" excludeAll (
//      ExclusionRule(organization = "com.sun.jdmk"),
//      ExclusionRule(organization = "com.sun.jmx"),
//      ExclusionRule(organization = "javax.jms"))
//  val main = play.Project(appName, appVersion, appDependencies).settings(
//    scalaVersion := "2.10.3" // Add your own project settings here
//    )
//
//}
