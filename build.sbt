scalaVersion := "2.11.7"

assemblyJarName in assembly := "boxtransfer.jar"

mainClass in assembly := Some("edu.nyu.acm.box.BoxTransfer")

libraryDependencies ++= Seq("com.box" % "box-java-sdk" % "2.0.0", "com.typesafe" % "config" % "1.2.1")
