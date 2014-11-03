package payment

import com.mongodb.casbah.commons.MongoDBObject
import database.DatabaseConnection
import java.util.Date
object PaymentRepository {

  def load(id: String): Payment = {
    val client = DatabaseConnection.getMongoClient
    val objectId = new org.bson.types.ObjectId(id);
    val collection = client("ewallethub")("payments")
    val payment = collection.findOne(MongoDBObject("_id" -> objectId)).get
    new Payment(payment.get("_id").toString(),
      BigDecimal.apply(payment.get("amount").toString()),
      payment.get("sender").toString(),
      payment.get("recipient").toString(),
      payment.get("currency").toString,
      EwalletOption.ewalletOptionByString(payment.get("ewallet").toString()),
      payment.get("IP").toString(),
      payment.get("statusUrl").toString(),
      getSafe(payment.get("timestamp")),
      payment.get("reference").toString())
  }

  def getPaymentsForRecipient(recipient: String) = {
    val client = DatabaseConnection.getMongoClient
    val collection = client("ewallethub")("payments")
    val payments = collection.find(MongoDBObject("recipient" -> recipient))
    for { payment <- payments } yield new Payment(payment.get("_id").toString(),
      BigDecimal.apply(payment.get("amount").toString()),
      payment.get("sender").toString(),
      payment.get("recipient").toString(),
      payment.get("currency").toString,
      EwalletOption.ewalletOptionByString(payment.get("ewallet").toString()),
      payment.get("IP").toString(),
      Option(payment.get("statusUrl")).toString(),
      getSafe(payment.get("timestamp")),
      Option(payment.get("reference")).toString())
  }

  def getSafe(o: Object): Date = {
    if (o == null)
      new Date()
    else PaymentTimestampFormat.format(o.toString())
  }
}