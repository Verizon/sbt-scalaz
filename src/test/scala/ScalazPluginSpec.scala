package verizon.build

import org.scalatest.{FlatSpec, Matchers}
import ScalazPlugin._

class ScalazPluginSpec extends FlatSpec with Matchers {
  behavior of "scalazCrossVersioners.default"

  it should "put the scalaz binary version after the version" in {
    scalazCrossVersioners.default("7.2.7")("0.1.0") should be ("0.1.0-scalaz-7.2")
  }

  it should "put the scalaz binary version before SNAPSHOT" in {
    scalazCrossVersioners.default("7.2.7")("0.1.0-SNAPSHOT") should be ("0.1.0-scalaz-7.2-SNAPSHOT")
  }

  it should "work with scalaz milestones" in {
    scalazCrossVersioners.default("7.3.0-M7")("0.1.0") should be ("0.1.0-scalaz-7.3.0-M7")
  }

  behavior of "scalazCrossVersioners.scalazStream_0_8"

  it should "not mark scalaz-7.1" in {
    scalazCrossVersioners.`scalaz-stream-0.8`("7.1.11")("0.1.0") should be ("0.1.0")
  }

  it should "put the 'a' suffix after the version in 7.2" in {
    scalazCrossVersioners.`scalaz-stream-0.8`("7.2.7")("0.1.0") should be ("0.1.0a")
  }

  it should "put the 'a' suffix before SNAPSHOT in 7.2" in {
    scalazCrossVersioners.`scalaz-stream-0.8`("7.2.7")("0.1.0-SNAPSHOT") should be ("0.1.0a-SNAPSHOT")
  }
}
