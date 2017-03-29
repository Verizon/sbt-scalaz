# sbt-scalaz

[![Build Status](https://travis-ci.org/Verizon/sbt-scalaz.svg?branch=master)](https://travis-ci.org/Verizon/sbt-scalaz)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.verizon.build/sbt-scalaz_2.10_0.13/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.verizon.build/sbt-scalaz_2.10_0.13/)


An sbt plugin to manage scalaz cross builds.  Features include:

* Rewriting your project version based on community naming conventions.
* Adding scalaz version-specific directories to your source path.
* Add a `forScalaz` operator to dependencies to try to find an appropriate one.

## Usage

1. Add to your `project/plugins.sbt`:

```scala
addSbtPlugin("io.verizon.build" % "sbt-scalaz" % sbtScalazVersion)
```

1. Set `version in ThisBuild` in your project's `version.sbt`:

```scala
version in ThisBuild := "1.0.0-SNAPSHOT"
```

1. Set `SCALAZ_VERSION` environment variable to initialize the `scalazVersion` setting in your build.  Travis users might include something like the following in `.travis.yml`:

```yaml
env:
  matrix:
  - SCALAZ_VERSION=7.2.9
  - SCALAZ_VERSION=7.1.11
```

## Version suffix

This plugin rewrites the `version` setting of your modules based on
`version in ThisBuild` and `scalazVersion`.  No consensus has been
reached in the community, so this plugin supports multiple strategies.
You choose it by setting the `scalazVersionRewriter`:

```scala
importp verizon.build.ScalazPlugin.autoImport._
import verizon.build.ScalazPlugin.scalazVersionRewriters

scalazVersionRewriter := scalazVersionRewriters.scalazStream_0_8
```

We recommend `default`, but `scalazStream_0_8` also has significant
traction in the community:

scalazVersionRewriter | version in ThisBuild | scalazVersion | version
--------------------- | -------------------- | ------------- | -------------------------
default               | 1.0.0                | 7.2.9         | 1.0.0-scalaz-7.2
default               | 1.0.0                | 7.1.11        | 1.0.0-scalaz-7.1
default               | 1.0.0-SNAPSHOT       | 7.2.9         | 1.0.0-scalaz-7.2-SNAPSHOT
scalazStream_0_8      | 1.0.0                | 7.2.9         | 1.0.0a
scalazStream_0_8      | 1.0.0                | 7.1.11        | 1.0.0
scalazStream_0_8      | 1.0.0-SNAPSHOT       | 7.2.9         | 1.0.0a-SNAPSHOT

## Dependencies

_sbt-scalaz_ tries to understand the Scalaz dependency ecosystem so
you don't have to.  _knobs_, _http4s_, and _specs2_ use three
different publishing conventions.  Use the `forScalaz` operator and
let _sbt-scalaz_ find the right convention:

```scala
libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-server" % "0.15.7" forScalaz scalazVersion.value,
  "io.verizon.knobs" %% "core" % "4.0.30" forScalaz scalazVersion.value,
  "org.specs2" %% "specs2-core" % "3.8.6" forScalaz scalazVersion.value 
)
```

under scalaz 7.2.9 becomes

```scala
libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-server" % "0.15.7a",
  "io.verizon.knobs" %% "core" % "4.0.30-scalaz-7.2",
  "org.specs2" %% "specs2-core" % "3.8.6"
)
```

and under scalaz 7.1.11 becomes

```scala
libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-server" % "0.15.7",
  "io.verizon.knobs" %% "core" % "4.0.30-scalaz-7.1",
  "org.specs2" %% "specs2-core" % "3.8.6-scalaz-7.1"
)
```

We do the dirty work so you don't have to.

