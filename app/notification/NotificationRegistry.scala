package notification

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.MongoDBObject
import database.DatabaseConnection
import java.util.Calendar

object NotificationRegistry {

  val calendar = Calendar.getInstance()

  def log(notification: Notification) = {
    val client = DatabaseConnection.getMongoClient
    val collection = client("ewallethub")("payments")
    val newDoc = MongoDBObject(
      "amount" -> notification.amount,
      "sender" -> notification.senderEmail,
      "currency" -> notification.currency,
      "ewallet" -> notification.ewallet.toString(),
      "status" -> notification.status.toString(),
      "transactionId" -> notification.transactionId,
      "recipientTransactionId" -> notification.recipientTransactionId,
      "timestamp" -> calendar.getTime())

    val id = new org.bson.types.ObjectId(notification.transactionId);
    collection.update(MongoDBObject("_id" -> id), $addToSet("notifications") $each (newDoc))

  }

  def logNotificationResponse(notification: Notification, url: String, response: String) = {
    val client = DatabaseConnection.getMongoClient
    val collection = client("ewallethub")("notificationResponse")
    val currentTime = calendar.getTime()
    val newDoc = MongoDBObject(
      "transactionId" -> notification.transactionId,
      "recipientTransactionId" -> notification.recipientTransactionId,
      "sender" -> notification.senderEmail,
      "url" -> url,
      "time" -> currentTime,
      "response" -> response)
    collection.insert(newDoc)
  }

  def loadNotificationResponseCount(transactionId: String): Integer = {
    val client = DatabaseConnection.getMongoClient
    val collection = client("ewallethub")("notificationResponse")
    collection.find(MongoDBObject("transactionId" -> transactionId)).size
  }
}