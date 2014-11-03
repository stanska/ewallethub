package payment

import org.specs2.mutable.Specification
import play.api.test._
import play.api.test.Helpers._
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import play.api.libs.json.Json
import play.api.libs.json.JsString
import org.codehaus.jackson.annotate.JsonValue
import play.api.libs.json.JsString
import play.api.libs.json.JsString
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.mvc.Result

object PaymentControllerTestHelper extends Specification {

  def makePayment(amount: String, recipient: String, sender: String, currency: String, ewallet: String, statusUrl: String, reference: String): Result = {
    val skrillJson = Json.obj(
      "amount" -> amount,
      "recipient" -> recipient,
      "sender" -> sender,
      "currency" -> currency,
      "ewallet" -> ewallet,
      "statusUrl" -> statusUrl,
      "reference" ->  reference)
    var request = FakeRequest(Helpers.POST, "payment", FakeHeaders(Seq(("Content-type", Seq("application/json")))), skrillJson).withJsonBody(skrillJson);
    payment.PaymentController.add(request)

  }
  
  def makePaymentReturnId(amount: String, recipient: String, sender: String, currency: String, ewallet: String, statusUrl: String, reference: String):String = {
    var paymentResult = makePayment(amount, recipient, sender, currency, ewallet, statusUrl, reference)
     val paymentResultString = contentAsString(paymentResult)
     
     val paymentIdTmp = paymentResultString.substring(paymentResultString.indexOf("ewallet_hub_transaction_id\" value=\"")+"ewallet_hub_transaction_id\" value=\"".length, paymentResultString.length()-1)
     paymentIdTmp.substring(0, paymentIdTmp.indexOf("\""))
  }
}

@RunWith(classOf[JUnitRunner])
class PaymentControllerTest extends Specification {
  "PaymentController" should {

    "post to skrill url with skrill specific parameters, should return skrill pay to form with prefilled sender recipient and amount in text/plain(!!!TBD)" in new WithApplication {
      val result = PaymentControllerTestHelper.makePayment("198.23", "stanska@gmail.com", "borko@gmail.com", "USD", "skrill", "statusurl","ref")
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")
      contentAsString(result) must contain("stanska@gmail.com")
      contentAsString(result) must contain("198.23")
      contentAsString(result) must contain("USD")
      contentAsString(result) must contain("ref")
    }

    val paypalJson = Json.obj(
      "amount" -> "198.23",
      "recipient" -> "stanska@gmail.com",
      "sender" -> "borko@gmail.com",
      "currency" -> "USD",
      "ewallet" -> "paypal",
      "statusUrl" -> "statusUrl")

    //    "post to paypal url with paypal specific parameters, should return paypal pay to form with prefilled sender recipient and amount in text/plain(!!!TBD)" in new WithApplication {
    //      var request = FakeRequest(Helpers.POST, "payment", FakeHeaders(Seq(("Content-type", Seq("application/json")))), paypalJson).withJsonBody(paypalJson);
    //      val result = payment.PaymentController.add(request)
    //      status(result) must equalTo(OK)
    //      contentType(result) must beSome("text/plain")
    //      charset(result) must beSome("utf-8")
    //      contentAsString(result) must contain("Stanka&#x20;Dalekova")
    //      contentAsString(result) must contain("<span class=\"amount\">&#x24;198&#x2e;23</span>")
    //      contentAsString(result) must contain("borko&#x40;gmail&#x2e;com")
    //    }
  }
}