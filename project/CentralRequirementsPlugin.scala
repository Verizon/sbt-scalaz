package verizon.build

import sbt._, Keys._
import xerial.sbt.Sonatype.autoImport.sonatypeProfileName

object CentralRequirementsPlugin extends AutoPlugin {

  override def trigger = allRequirements

  override def requires = RigPlugin

  override lazy val projectSettings = Seq(
    sonatypeProfileName := "io.verizon",
    pomExtra in Global := {
      <developers>
        <developer>
          <id>rossabaker</id>
          <name>Ross A. Baker</name>
          <url>http://github.com/rossabaker</url>
        </developer>
      </developers>
    },
    licenses := Seq("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0.html")),
    homepage := Some(url("https://github.com/Verizon/sbt-scalaz/")),
    scmInfo := Some(ScmInfo(url("https://github.com/Verizon/sbt-scalaz"),
                                "git@github.com:Verizon/sbt-scalaz.git"))
  )
}
