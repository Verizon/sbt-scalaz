package verizon.build

import sbt._, Keys._

object ScalazPlugin extends AutoPlugin {
  object autoImport {
    val scalazVersion = settingKey[String]("scalaz version")

    val scalazVersionRewriter = settingKey[(String, String) => String]("adjust the version based on a scalaz version")
  }

  import autoImport._

  override def trigger = allRequirements

  override lazy val projectSettings = Seq(
    scalazVersion := sys.env.get("SCALAZ_VERSION").getOrElse("7.2.7"),
    scalazVersionRewriter := scalazBinaryVersionInQualifier,
    version := scalazVersionRewriter.value(version.value, scalazVersion.value)
  )

  val scalazBinaryVersionInQualifier = { (version: String, scalazVersion: String) =>
    version match {
      case VersionNumber(numbers, tags, extras) =>
        val qualifier = scalazVersion match {
          case VersionNumber(Seq(zMajor, zMinor, _*), _, _) =>
            s"scalaz-$zMajor.$zMinor"
          case _ =>
            s"scalaz-$scalazVersion"
        }
        numbers.mkString(".") + (qualifier +: tags).mkString("-", "-", "") + extras.mkString("")
      case _ =>
        // Can't determine scalaz binary compatibility. *shrug*
        s"${version}-scalaz-${scalazVersion}"
    }
  }

  /**
   * This convention is prevalent in the community (scalaz-stream,
   * http4s < 0.16, argonaut-6.1), but not recommended as it breaks
   * semantic versioning.
   */
  val scalazSevenTwoSuffixA = { (version: String, scalazVersion: String) =>
    VersionNumber(scalazVersion).numbers match {
      case Seq(7, 2, _*) =>
        version match {
          case VersionNumber(numbers, tags, extras) =>
            numbers.mkString(".") + "a" + (tags match {
              case Seq() => ""
              case ts => ts.mkString("-", "-", "")
            }) + extras.mkString("")
        }
      case _ =>
        version
    }
  }
}
