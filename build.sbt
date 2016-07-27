

val root = (project in file("."))
	.settings(Seq(
		name := "airdb",

		version := "0.1-SNAPSHOT",

		scalaVersion := "2.11.8",

		scalacOptions ++= Seq(
			"-Xfatal-warnings",
			"-feature"
		)
	))