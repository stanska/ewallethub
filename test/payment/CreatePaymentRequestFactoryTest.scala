package payment

import org.specs2.mutable.Specification
import play.api.test.WithApplication
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers
import play.api.test.FakeHeaders
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CreatePaymentRequestFactoryTest extends Specification {
  val amount = 198.23
  val sender = "borko@gmail.com"
  val recipient = "stanska@gmail.com"
  val currency = "USD"
  val statusUrl = "statusUrl"
  val reference = "reference"
  val json = Json.obj(
    "amount" -> amount,
    "recipient" -> recipient,
    "sender" -> sender,
    "currency" -> currency,
    "ewallet" -> EwalletOption.skrill.toString(),
    "statusUrl" -> statusUrl,
    "reference" -> reference)
  var jsonrequest = FakeRequest(Helpers.POST, "payment", FakeHeaders(Seq(("Content-type", Seq("application/json")))), json).withJsonBody(json);
  val map = Map(("x", 24), ("y", 25), ("z", 26));

  // var postrequest = FakeRequest(Helpers.POST, "payment", FakeHeaders(),map).withFormUrlEncodedBody(("amount", amount.toString),("currency", currency), ("recipient",recipient), ("sender", sender));
  var getrequest = new FakeRequest(Helpers.GET, "payment?amount=" + amount + "&recipient=" + recipient + "&sender=" + sender + "&currency=" + currency + "&ewallet=" + EwalletOption.skrill + "&statusUrl=" + statusUrl + "&reference=" + reference, FakeHeaders(), "").withTextBody("");

  "CreatePaymentRequestFactory" should {
    "return CreatePaymentRequest from json Request" in new WithApplication {
      val paymentRequest = CreatePaymentRequestFactory.newInstance(jsonrequest)
      paymentRequest.amount.doubleValue must equalTo(amount)
      paymentRequest.sender must equalTo(sender)
      paymentRequest.currency must equalTo(currency)
      paymentRequest.recipient must equalTo(recipient)
      paymentRequest.statusUrl must equalTo(statusUrl)
      paymentRequest.ewalletOption must equalTo(EwalletOption.skrill)
      paymentRequest.reference must equalTo(reference)
    }

    //    "return CreatePaymentRequest from post Request" in new WithApplication {
    //      val paymentRequest = CreatePaymentRequestFactory.newInstance(postrequest)
    //      paymentRequest.amount.doubleValue must equalTo(amount)
    //      paymentRequest.sender must equalTo(sender)
    //      paymentRequest.currency must equalTo(currency)
    //      paymentRequest.recipient must equalTo(recipient)
    //      paymentRequest.ewalletOption must	 equalTo(EwalletOption.skrill)
    //    }
    //    
    "return CreatePaymentRequest from get Request" in new WithApplication {
      val paymentRequest = CreatePaymentRequestFactory.newInstance(getrequest)
      paymentRequest.amount.doubleValue must equalTo(amount)
      paymentRequest.sender must equalTo(sender)
      paymentRequest.currency must equalTo(currency)
      paymentRequest.recipient must equalTo(recipient)
      paymentRequest.ewalletOption must equalTo(EwalletOption.skrill)
      paymentRequest.statusUrl must equalTo(statusUrl)
      paymentRequest.reference must equalTo(reference)
    }

    "return CreatePaymentRequest from empty Request" in new WithApplication {
      val paymentRequest = CreatePaymentRequestFactory.newInstance(FakeRequest()) must throwA[Exception]
    }
  }
}