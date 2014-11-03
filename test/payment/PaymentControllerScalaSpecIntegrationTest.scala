package payment

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

import play.api.libs.json.Json
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import play.api.test.FakeHeaders
import play.api.test.FakeRequest
import play.api.test.Helpers
import play.api.test.Helpers.OK
import play.api.test.Helpers.charset
import play.api.test.Helpers.contentAsString
import play.api.test.Helpers.contentType
import play.api.test.Helpers.status
import play.api.test.WithApplication
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PaymentControllerScalaSpecIntegrationTest extends Specification with Mockito{
  "PaymentController" should {
    val skrillJson = Json.obj(
      "amount" -> "198.23",
      "recipient" -> "stanska@gmail.com",
      "sender" -> "borko@gmail.com",
      "currency" -> "USD",
      "ewallet" -> "skrill",
      "statusUrl" -> "statusUrl",
      "reference" -> "reference")

    "post to skrill url with skrill specific parameters, should return skrill pay to form with prefilled sender recipient and amount in text/plain(!!!TBD)" in new WithApplication {
      var request = FakeRequest(Helpers.POST, "payment", FakeHeaders(Seq(("Content-type", Seq("application/json")))), skrillJson).withJsonBody(skrillJson);
      val result = payment.PaymentController.add(request)
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")
      contentAsString(result) must contain("stanska@gmail.com")
      contentAsString(result) must contain("198.23");
      contentAsString(result) must contain("USD")
      contentAsString(result) must contain("borko@gmail.com")
      contentAsString(result) must contain("reference")
    }

    val paypalJson = Json.obj(
      "amount" -> "198.23",
      "recipient" -> "stanska@gmail.com",
      "sender" -> "borko@gmail.com",
      "currency" -> "USD",
      "ewallet" -> "paypal",
      "statusUrl" -> "statusUrl",
      "reference" -> "reference")

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