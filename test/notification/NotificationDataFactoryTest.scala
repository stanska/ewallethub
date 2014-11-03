package notification

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import payment.EwalletOption
import payment.PaymentStatus

@RunWith(classOf[JUnitRunner])
class NotificationDataFactoryTest extends Specification {
//  val transactionId = "skrill transaction id"
//  val ewalletHubTransactionId = "123"
//  val amount = "1.23"
//  val currency = "EUR"
//  val sender = "test@test.com"
//  "NotificationDataFactory" should {
//    "parse data for successful skrill 2 status" in {
//      val parameters = Map("ewallet_option" -> Seq("skrill"), "ewallet_hub_transaction_id" -> Seq(ewalletHubTransactionId), "transaction_id" -> Seq(transactionId),
//        "amount" -> Seq(amount), "currency" -> Seq(currency), "pay_from_email" -> Seq(sender), "status" -> Seq("2"))
//      val notificationData = NotificationDataFactory.newInstance(parameters);
//      notificationData.ewallet must equalTo(EwalletOption.skrill)
//      notificationData.transactionId must equalTo(ewalletHubTransactionId)
//      notificationData.recipientTransactionId must equalTo(transactionId)
//      notificationData.amount must equalTo(amount)
//      notificationData.currency must equalTo(currency)
//      notificationData.senderEmail must equalTo(sender)
//      notificationData.status must equalTo(PaymentStatus.PROCESSED)
//    }
//
//    "parse data for no status skrill payment throws IllegalStateException" in {
//      val parameters = Map("ewallet_option" -> Seq("skrill"), "ewallet_hub_transaction_id" -> Seq(ewalletHubTransactionId), "transaction_id" -> Seq(transactionId),
//        "amount" -> Seq(amount), "currency" -> Seq(currency), "pay_from_email" -> Seq(sender))
//      val notificationData = NotificationDataFactory.newInstance(parameters) must throwA[IllegalStateException];
//    }
//    "parse data for unsuccessful skrill -2 payment" in {
//      val parameters = Map("ewallet_option" -> Seq("skrill"), "ewallet_hub_transaction_id" -> Seq(ewalletHubTransactionId), "transaction_id" -> Seq(transactionId),
//        "amount" -> Seq(amount), "currency" -> Seq(currency), "pay_from_email" -> Seq(sender), "status" -> Seq("-2"))
//      val notificationData = NotificationDataFactory.newInstance(parameters);
//      notificationData.ewallet must equalTo(EwalletOption.skrill)
//      notificationData.transactionId must equalTo(ewalletHubTransactionId)
//      notificationData.recipientTransactionId must equalTo(transactionId)
//      notificationData.amount must equalTo(amount)
//      notificationData.currency must equalTo(currency)
//      notificationData.senderEmail must equalTo(sender)
//      notificationData.status must equalTo(PaymentStatus.FAILED)
//    }
//
//    "return null data for none and paypal" in {
//      val parameters = Map("ewallet_hub_transaction_id" -> Seq(ewalletHubTransactionId), "transaction_id" -> Seq(transactionId),
//        "amount" -> Seq(amount), "currency" -> Seq(currency), "pay_from_email" -> Seq(sender), "status" -> Seq("-2"))
//      val notificationData = NotificationDataFactory.newInstance(parameters)
//      notificationData must equalTo(null)
//    }
//  }
}