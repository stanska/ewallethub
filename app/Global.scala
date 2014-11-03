import cart.{CartItem, Cart, CartTable}
import play.api._
//import play.api.mvc.EssentialAction
//import filters.LoggingFilter
import com.mongodb.casbah.MongoClient
import database.DatabaseConnection
import play.api.GlobalSettings
import play.api.Application

import play.api.db._

import scala.slick.driver.PostgresDriver.simple._
import play.api.Play.current
//import Database.threadLocalSession
//import scala.slick.driver.PostgresDriver.simple._

//import scala.slick.lifted.TableQuery

object Global extends GlobalSettings {

//  override def doFilter(action: EssentialAction) = LoggingFilter(action)

  override def onStart(app: Application) {
    Logger.info("Mongo client initialized")
    DatabaseConnection.setMongoClient(MongoClient("localhost", 27017))
    //on start, start notificator for all notification logs, that do not have entry in notificationResponse collection
    //but how to make this fast query

    lazy val database = Database.forDataSource(DB.getDataSource())
//  }
    database.withSession {
      implicit session: Session =>
      val t = TableQuery[CartTable]
//      t.ddl.create
//       t.insert(new Cart("info"))
//       t.insert(new Cart("info"))
      t.filter(_.id > 0).foreach(println)
    }
  }
}
//
//class Cart(tag:Tag) extends Table[(Int)](tag, "CART") {
//  def id = column[Int]("ID", O.PrimaryKey) // This is the primary key column
//  def * = (id)
//}
//
//case class Account(id: Option[Long], email: String, password: String)
//
//object Accounts extends Table[Account]("account") {
//
//  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
//  def email = column[String]("email")
//  def password = column[String]("password")
//  def * = id.? ~ email ~ password <> (Account, Account.unapply _)
//}