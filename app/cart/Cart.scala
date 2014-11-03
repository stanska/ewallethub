package cart

import play.api.libs.json._

import scala.slick.driver.PostgresDriver.simple._
import scala.slick.lifted.ProvenShape



case class Cart(id:Int, info:String){//}, data:CartItemData) {
  def this(info:String) = this(1, info)//, new CartItemData("name","descr", 1))
}

class CartTable(tag:Tag) extends Table[Cart](tag, "CART") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def info = column[String]("INFO")
//
//  def json =   JsObject(Seq(
//    "id" -> JsString(id.toString()) //TODO how to get row
//    //        "items" -> JsArray(
//    //          cart.items.map(f => JsObject(Seq(
//    //            "name" -> JsString(f.itemData.name),
//    //            "description" -> JsString(f.itemData.description),
//    //            "quantity" -> JsNumber(f.itemData.quantity)
//    //          ))
//    //         ))
//  ))
//  json
  def * : ProvenShape[Cart] = (id, info)<> ((Cart.apply _).tupled, Cart.unapply)
}

object Cart {
  def toCaseClass(c:(Int, String)) = (Cart.apply _).tupled(c)

  implicit val cartReads = Json.reads[Cart]
  implicit val cartWrites = Json.writes[Cart]

}
//  implicit val cartFormats = Json.format[Cart]
//  def createJsonResponse(cart: Cart): JsValue = {
//    val json =
//      JsObject(Seq(
//        "id" -> JsNumber(-1) //TODO how to get row
        //        "items" -> JsArray(
        //          cart.items.map(f => JsObject(Seq(
        //            "name" -> JsString(f.itemData.name),
        //            "description" -> JsString(f.itemData.description),
        //            "quantity" -> JsNumber(f.itemData.quantity)
        //          ))
        //         ))
//      ))
//    json
//  }
// TODO JSON and play
//}
//case class CartId(id: Long) extends AnyVal with BaseId
//
//object CartId extends IdCompanion[CartId]
//
//case class Cart(id: Long,
//                user: String
//                user: String
//                 ) extends WithId[CartId] {
//  def base = user
//
//  override def * = id.? ~: base <> (Cart.apply _, Cart.unapply _)
//
//}
//
//object Cart extends IdTable[CartId, Cart]("CART") {
//  //https://github.com/VirtusLab/activator-play-advanced-slick/blob/master/app/model/User.scala
//  override def insertOne(elem: Cart)(implicit session: Session): CartId =
//    saveBase(base, Cart.unapply _)(elem)
//  // implicit val cartReads = Json.reads[Cart]
//  //implicit val cartWrites = Json.writes[Cart]
//
//  def json(cart: Cart): JsValue = {
//    val json =
//      JsObject(Seq(
//        "id" -> JsNumber(-1) //TODO how to get row
//        //        "items" -> JsArray(
//        //          cart.items.map(f => JsObject(Seq(
//        //            "name" -> JsString(f.itemData.name),
//        //            "description" -> JsString(f.itemData.description),
//        //            "quantity" -> JsNumber(f.itemData.quantity)
//        //          ))
//        //         ))
//      ))
//    json
//  }
//
//}

