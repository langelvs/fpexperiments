resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

logLevel := Level.Warn

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.3.0-SNAPSHOT")
