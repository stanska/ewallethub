package payment

import java.util.Calendar

import com.mongodb.casbah.commons.MongoDBObject

import database.DatabaseConnection

object PaymentFactory {
  val calendar = Calendar.getInstance()

  def add(paymentRequest: CreatePaymentRequest, remoteIP: String): Payment = {

    val client = DatabaseConnection.getMongoClient
    val collection = client("ewallethub")("payments")
    val currentTime = calendar.getTime()
    //gets data when a request comes in and writes it to mongo
    val newDoc = MongoDBObject(
      "amount" -> paymentRequest.amount.toString,
      "sender" -> paymentRequest.sender,
      "recipient" -> paymentRequest.recipient,
      "currency" -> paymentRequest.currency,
      "ewallet" -> paymentRequest.ewalletOption.toString,
      "IP" -> remoteIP,
      "statusUrl" -> paymentRequest.statusUrl,
      "reference" -> paymentRequest.reference,
      "timestamp" -> currentTime)
    collection.insert(newDoc)
    new Payment(paymentRequest, newDoc.get("_id").toString, remoteIP, paymentRequest.statusUrl, currentTime, paymentRequest.reference)
  }

}