package cart

import play.api.mvc.{ Action, Controller}
import play.api.libs.json._

import play.api.db._

import scala.slick.driver.PostgresDriver.simple._
import play.api.Play.current
import play.api.Play
object CartController extends Controller {
  def newCart = {
    Action {
      val carts = TableQuery[CartTable]
//      lazy val database = Database.forDataSource(DB.getDataSource())
      import com.mchange.v2.c3p0.ComboPooledDataSource

      val database = { //move this to trait
        val ds = new ComboPooledDataSource
        val environment:String = Play.current.configuration.getString("environment").getOrElse("test")
        val prefix = "dbPool"
        ds.setDriverClass(Play.current.configuration.getString(s"$prefix.$environment.driver").get)
        ds.setJdbcUrl(Play.current.configuration.getString(s"$prefix.$environment.url").get)
        ds.setUser(Play.current.configuration.getString(s"$prefix.$environment.user").get)
        ds.setPassword(Play.current.configuration.getString(s"$prefix.$environment.password").get)
        Database.forDataSource(ds)
      }

      database.withSession {
        implicit session: Session =>
//          carts.ddl.drop
//          carts.ddl.create
//          carts.insert(new Cart("test"))
//          carts.insert(new Cart("test"))
        println("1")
//         Ok("Done")
          Ok(Json.toJson(carts.filter(_.id === 1).firstOption.get))

      }
    }
//    DBAction { implicit rs =>
////      val carts = TableQuery[Cart]
////      suppliers.map(a => println(" id  " + a.id))
//      import play.api.Play.current
//
//      //play and slick
//      //val database  = Database.forDataSource(DB.getDataSource())
//      //database.withSession{ implicit session : Session =>
////      TableQuery[Cart].ddl.create
//      //}
//    Ok("test page" )//+ cart)
//    }
  }
//
//  def addItem(cartId: Long) = {
//    Action {
//      request =>
//        val cartItemDataJson = request.body.asJson.get
//        val cartItemData = cartItemDataJson.as[CartItemData]
//        Ok(Json.toJson(new CartItem(1, cartItemData)))
//    }
//  }
//
//  def showCart(cartId: Long) = {
//    Action {
//      Ok("show cart") //Json.toJson(new Cart(1, List )))
//    }
//  }
}
