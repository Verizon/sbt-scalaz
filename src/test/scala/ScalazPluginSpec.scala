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

import org.scalatest.{FlatSpec, Matchers}
import ScalazPlugin._

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
}
