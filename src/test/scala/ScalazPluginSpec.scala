package verizon.build

import org.scalatest.{FlatSpec, Matchers}
import ScalazPlugin._

class ScalazPluginSpec extends FlatSpec with Matchers {
  behavior of "scalazBinaryVersionInQualifier"

  it should "put the scalaz binary version after the version" in {
    scalazBinaryVersionInQualifier("0.1.0", "7.2.7") should be ("0.1.0-scalaz-7.2")
  }

  it should "put the scalaz binary version before SNAPSHOT" in {
    scalazBinaryVersionInQualifier("0.1.0-SNAPSHOT", "7.2.7") should be ("0.1.0-scalaz-7.2-SNAPSHOT")
  }

  behavior of "scalazSevenTwoSuffixA"

  it should "not mark scalaz-7.1" in {
    scalazSevenTwoSuffixA("0.1.0", "7.1.11") should be ("0.1.0")
  }

  it should "put the 'a' suffix after the version" in {
    scalazSevenTwoSuffixA("0.1.0", "7.2.7") should be ("0.1.0a")
  }

  it should "put the 'a' suffix before SNAPSHOT" in {
    scalazSevenTwoSuffixA("0.1.0-SNAPSHOT", "7.2.7") should be ("0.1.0a-SNAPSHOT")
  }
}
