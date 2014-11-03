package cart

import play.api.libs.json._

case class CartItem(id: Long, itemData: CartItemData)

object CartItem {
  implicit val cartItemReads = Json.reads[CartItem]
  implicit val cartItemWrites = Json.writes[CartItem]
}

