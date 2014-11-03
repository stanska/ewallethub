package database

import play.api.db.DB
import play.api.Application

import scala.slick.driver.PostgresDriver.simple._

//trait DBable {
//
//  val SLICK_DRIVER = "slick.db.driver"
//  val DEFAULT_SLICK_DRIVER = "scala.slick.driver.PostgresDriver"
//
//  def getDriver(implicit app : Application)  = {
//    val driverClass = app.configuration.getString(SLICK_DRIVER).getOrElse(DEFAULT_SLICK_DRIVER)
//    val driver = singleton[ExtendedProfile](driverClass)
//    driver
//  }

//  def getDb(implicit app : Application) = {
//    Database.forDataSource(DB.getDataSource())
//  }
//
//  private def singleton[T](name : String)(implicit man: Manifest[T]) : T =
//    Class.forName(name + "$").getField("MODULE$").get(man.runtimeClass).asInstanceOf[T]
//
//}
//
//import slick.driver.ExtendedProfile
//
//class DAL(override val profile: ExtendedProfile) extends UserComponent with Profile {
//
//  import profile.simple._
//
//  def create(implicit session: Session): Unit = {
//    Users.ddl.create //helper method to create all tables
//  }
//}