import verizon.build.ScalazPlugin

version in ThisBuild := "0.1.0"

TaskKey[Unit]("check") := {
  val expectedVersion = "0.1.0-scalaz-7.2"
  if (version.value != expectedVersion)
    sys.error(s"""Expected version "${expectedVersion}", got "${version.value}""")
}
