# scala-git-markers

The worst Scala library you've seen.

## What is this???

This is a realization of a cursed idea based on [this tweet](https://twitter.com/kubukoz/status/1590135952886075393) and [this video](https://www.youtube.com/watch?v=rSk_mea4U1E).

## Usage

In sbt:

```scala
libraryDependencies += "org.polyvariant" %% "scala-git-markers" % "0.1.0"
// For Scala.js, Scala Native
libraryDependencies += "org.polyvariant" %%% "scala-git-markers" % "0.1.0"
```

```scala
import org.polyvariant.gitmarkers._
```

Now, whenever you see git's conflict markers, for example:

```scala
val example =
<<<<<<< HEAD
"foo"
=======
"baz"
>>>>>>> bar
```

you'll be able to compile your code! Just make sure you define a value for the second ref you're merging (in this case, `bar`):

```scala
val bar = "bar"
```

now you can do this:

```scala
example.describe
//   Found conflict:
// - First ref: HEAD
//   Value: foo
// - Second ref: bar
//   Value: baz
```
