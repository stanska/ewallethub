package cart

import play.api.libs.json._

case class CartItemData(name:String, description:String, quantity:Int)
object CartItemData {
  implicit val cartItemDataReads = Json.reads[CartItemData]
  implicit val cartItemDataWrites = Json.writes[CartItemData]
}
