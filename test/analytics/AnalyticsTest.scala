package analytics

import org.scalatest.FunSpec
import org.scalatest.GivenWhenThen
import payment.Payment
import org.specs2.matcher.ShouldMatchers
import database.DatabaseConnection
import payment.PaymentFactory
import com.mongodb.casbah.MongoClient
import payment.CreatePaymentRequest
import payment.EwalletOption
import org.scalatest.BeforeAndAfterAll

class AnalyticsTest extends FunSpec
  with ShouldMatchers
  with GivenWhenThen
  with BeforeAndAfterAll {
  override def beforeAll: Unit = {
    DatabaseConnection.setMongoClient(MongoClient("localhost", 27017))
    PaymentFactory.add(new CreatePaymentRequest("12.00",
      "sender email",
      "recipient@email.com",
      "EUR",
      EwalletOption.skrill,
      "statusUrl", "reference"), "test ip")
  }
  
  describe("Payment Analytics") {
    it("show payments for given recipient") {
      info("currently just list the payment without timerange restriction")
      Given("A recipient")
      val recipient: String = "recipient@email.com"

      When("analytics are obtained for the given recipient")
      val payments = PaymentAnalytics.getPaymentsForRecipient(recipient)

      Then("all payments to this recipient are retrieved")
      info("current payment count for recipient " + recipient + " is " + payments.size)
      for (payment: Payment <- payments) {
        payment.recipient should be(recipient)
      }
    }
    it("show pyments for given recipient with time range restriction") {
      Given("TBD given")
      When("TBD when")
      Then("TBD then")
      pending
    }
    it("the payments for a given recipient can be categorized by geo range TBD") {
      pending
    }
    it("the payments for a given recipient can be categorized by Payment Option") {
      pending
    }
    it("the payments for a given recipient can be categorized by Amount") {
      pending
    }
  }
}