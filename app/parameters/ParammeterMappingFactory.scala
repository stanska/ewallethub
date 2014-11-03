package parameters

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoCollection
import payment.EwalletOption
import payment.EwalletOption._;

object ParammeterMappingFactory {
  def readParameters(ewallet: EwalletOption): Iterable[Parameter] = {
    val mongoConnection = MongoConnection()
    val parametersCollection = mongoConnection("test")("parameters")
    //    val parametersCollection = mongoConnection("test")("parameters")
    //    val bread1 = MongoDBObject("name" -> "parrys",
    //      "price" -> "10 INR")
    //    val bread2 = MongoDBObject("name" -> "breadAndMore")
    //    mongoColl += bread1
    //    mongoColl += bread2
    //    mongoCollection
    parametersCollection.find()
    for { x <- parametersCollection } yield new Parameter(x.get("_id").toString(), x.get("name").toString(), ParameterType.text, 0, 0, 0, 0)
  }
  //USING
  //        val parameters: Iterable[Parameter] = ParammeterMappingFactory.readParameters(paymentRequest.ewalletOption)
  //        for { x: Parameter <- parameters } yield println("value " + x.from + x.to)
}