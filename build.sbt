lazy val common = Seq {

name := "ScalaFinalTest"

  version := "1.0"

  scalaVersion := "2.12.3"

  libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.3" % "test"
}

lazy val Account  = project.settings(common)

lazy val apiResources  = project.dependsOn(inventory,checkoutServices,Account,notification)
  .settings(common)
lazy val checkoutServices = project.dependsOn(inventory).settings(common)

lazy val dashboard = project.settings(common).dependsOn(apiResources)

lazy val inventory = project.settings(common)

lazy val notification= project.settings(common)

lazy val root = project.in(file(".")).aggregate(Account,apiResources,checkoutServices,
  dashboard,inventory,notification).dependsOn(Account,apiResources,checkoutServices,
  dashboard,inventory,notification)