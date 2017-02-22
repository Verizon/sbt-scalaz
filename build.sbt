organization := "io.verizon.build"

name := "sbt-scalaz"

scalacOptions ++= Seq("-deprecation", "-feature")

sbtPlugin := true

ScriptedPlugin.scriptedSettings

scriptedLaunchOpts ++= Seq(
  "-Xmx1024M",
  "-Dplugin.version=" + version.value,
  "-Dscripted=true")

scriptedBufferLog := false

fork := true

licenses := Seq("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0.html"))

homepage := Some(url("https://github.com/verizon/sbt-scalaz"))

scmInfo := Some(ScmInfo(url("https://github.com/verizon/sbt-scalaz"),
                            "git@github.com:verizon/sbt-scalaz.git"))

// To sync with Maven central, you need to supply the following information:
pomExtra in Global := {
  <developers>
    <developer>
      <id>rossabaker</id>
      <name>Ross A. Baker</name>
      <url>http://rossabaker.com/</url>
    </developer>
  </developers>
}

sonatypeProfileName := "io.verizon"

pomPostProcess := { identity }

addCommandAlias("validate", ";test;scripted")

addSbtPlugin("org.xerial.sbt"    % "sbt-sonatype"  % "1.1")
addSbtPlugin("com.jsuereth"      % "sbt-pgp"       % "1.0.0")

addCommandAlias("validate", ";test;scripted")
