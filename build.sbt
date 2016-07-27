

val root = (project in file("."))
	.settings(SbtScalariform.defaultScalariformSettings)
	.settings(Seq(
		name := "airdb",

		version := "0.1-SNAPSHOT",

		scalaVersion := "2.11.8",

		libraryDependencies ++= Seq(
			"org.specs2" %% "specs2-core" % "3.8.4" % "test"
		),

		scalacOptions in Test ++= Seq(
			"-Yrangepos"
		),

		scalacOptions ++= Seq(
			"-Xfatal-warnings",
			"-feature"
		)
	))