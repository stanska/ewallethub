name := "ewallethub"

version := "1.0-SNAPSHOT"

//lazy val root = (project in file(".")).enablePlugins(PlayScala)
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  jdbc, // Play's web services module
  javaCore,
  "org.mongodb" %% "casbah" % "2.6.1",
//  "org.webjars" % "bootstrap" % "2.3.1",
//  "org.webjars" % "flot" % "0.8.0",
  //js,less url access
  "org.webjars" %% "webjars-play" % "2.2.0",
  "org.webjars" % "bootstrap" % "3.0.1",
  //testing frameworks
  "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test",
  "org.mockito" % "mockito-all" % "1.9.0" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.4" % "test",
   //scala rx, future and the like
  "com.netflix.rxjava" % "rxjava-scala" % "0.16.0",
  "com.netflix.rxjava" % "rxjava-async-util" % "0.16.0",
  //postgre
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  //slick
  "com.typesafe.slick" %% "slick" % "2.0.2",
  "com.typesafe.play" %% "play-slick" % "0.6.0.1",
  //c3p0 DB connection pool, since slick does not have any connection pool
  "jp.furyu" %% "play-c3p0-plugin" % "0.2.1",
  //h2 used for testing
  "com.h2database" % "h2" % "1.3.167",
  //akka exclusion, probably not needed any more, TODO:test if needed
  "com.typesafe.akka" %% "akka-actor" % "2.1.0" % "test"
    excludeAll (
    ExclusionRule(organization = "akka.actor.Scheduler")),
  "com.typesafe.akka" %% "akka-testkit" % "2.1.0" % "test"
    excludeAll (
    ExclusionRule(organization = "akka.actor.Scheduler")),
  "org.scalacheck" %% "scalacheck" % "1.10.1" % "test"
)

play.Project.playScalaSettings