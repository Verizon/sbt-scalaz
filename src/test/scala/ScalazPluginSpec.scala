//: ----------------------------------------------------------------------------
//: Copyright (C) 2017 Verizon.  All Rights Reserved.
//:
//:   Licensed under the Apache License, Version 2.0 (the "License");
//:   you may not use this file except in compliance with the License.
//:   You may obtain a copy of the License at
//:
//:       http://www.apache.org/licenses/LICENSE-2.0
//:
//:   Unless required by applicable law or agreed to in writing, software
//:   distributed under the License is distributed on an "AS IS" BASIS,
//:   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//:   See the License for the specific language governing permissions and
//:   limitations under the License.
//:
//: ----------------------------------------------------------------------------
package verizon.build

import sbt._
import org.scalatest.{FlatSpec, Matchers}
import ScalazPlugin._
import ScalazPlugin.autoImport._

class ScalazPluginSpec extends FlatSpec with Matchers {
  behavior of "scalazVersionRewriters.default"

  it should "put the scalaz binary version after the version" in {
    scalazVersionRewriters.default("0.1.0", "7.2.9") should be ("0.1.0-scalaz-7.2")
  }

  it should "put the scalaz binary version before SNAPSHOT" in {
    scalazVersionRewriters.default("0.1.0-SNAPSHOT", "7.2.9") should be ("0.1.0-scalaz-7.2-SNAPSHOT")
  }

  it should "work with scalaz milestones" in {
    scalazVersionRewriters.default("0.1.0", "7.3.0-M7") should be ("0.1.0-scalaz-7.3.0-M7")
  }

  behavior of "scalazVersionRewriters.scalazStream_0_8"

  it should "not mark scalaz-7.1" in {
    scalazVersionRewriters.scalazStream_0_8("0.1.0", "7.1.11") should be ("0.1.0")
  }

  it should "put the 'a' suffix after the version in 7.2" in {
    scalazVersionRewriters.scalazStream_0_8("0.1.0", "7.2.9") should be ("0.1.0a")
  }

  it should "put the 'a' suffix before SNAPSHOT in 7.2" in {
    scalazVersionRewriters.scalazStream_0_8("0.1.0-SNAPSHOT", "7.2.9") should be ("0.1.0a-SNAPSHOT")
  }

  behavior of "forScalaz"

  it should "understand helm" in {
    ("io.verizon.helm" %% "core" % "1.3.77" forScalaz "7.1.11").revision should be ("1.3.77-scalaz-7.1")
    ("io.verizon.helm" %% "core" % "1.3.77" forScalaz "7.2.9").revision should be ("1.3.77-scalaz-7.2")
  }

  behavior of "forScalaz"

  it should "understand knobs" in {
    ("io.verizon.knobs" %% "core" % "3.12.28" forScalaz "7.1.11").revision should be ("3.12.28")
    ("io.verizon.knobs" %% "core" % "3.12.28" forScalaz "7.2.9").revision should be ("3.12.28a")
    ("io.verizon.knobs" %% "core" % "4.0.30" forScalaz "7.1.11").revision should be ("4.0.30-scalaz-7.1")
    ("io.verizon.knobs" %% "core" % "4.0.30" forScalaz "7.2.9").revision should be ("4.0.30-scalaz-7.2")
  }

  it should "understand quiver" in {
    ("io.verizon.quiver" %% "core" % "5.5.14" forScalaz "7.1.11").revision should be ("5.5.14-scalaz-7.1")
    ("io.verizon.quiver" %% "core" % "5.5.14" forScalaz "7.2.9").revision should be ("5.5.14-scalaz-7.2")
  }

  it should "understand http4s" in {
    ("org.http4s" %% "http4s-core" % "0.15.7" forScalaz "7.1.11").revision should be ("0.15.7")
    ("org.http4s" %% "http4s-core" % "0.15.7" forScalaz "7.2.9").revision should be ("0.15.7a")
  }

  it should "understand jawn-streamz" in {
    ("org.http4s" %% "jawn-streamz" % "0.10.1" forScalaz "7.1.11").revision should be ("0.10.1")
    ("org.http4s" %% "jawn-streamz" % "0.10.1" forScalaz "7.2.9").revision should be ("0.10.1a")
  }

  it should "understand scalaz-stream" in {
    ("org.scalaz.stream" %% "scalaz-stream" % "0.7.3" forScalaz "7.0.6").revision should be ("0.7.3")
    ("org.scalaz.stream" %% "scalaz-stream" % "0.7.3" forScalaz "7.1.11").revision should be ("0.7.3a")
    ("org.scalaz.stream" %% "scalaz-stream" % "0.8.6" forScalaz "7.1.11").revision should be ("0.8.6")
    ("org.scalaz.stream" %% "scalaz-stream" % "0.8.6" forScalaz "7.2.9").revision should be ("0.8.6a")
  }

  it should "understand specs2" in {
    ("org.specs2" %% "specs2-core" % "3.8.6" forScalaz "7.1.11").revision should be ("3.8.6-scalaz-7.1")
    ("org.specs2" %% "specs2-core" % "3.8.6" forScalaz "7.2.9").revision should be ("3.8.6")
  }
}
