package payment

import payment.EwalletOption._
import play.api.test.Helpers._
import play.api.libs.json.Json
import org.scalatest.mock.MockitoSugar._
import org.scalatest.mock._
import play.api.libs.ws.WS
import org.junit.Test
import play.api.test.FakeRequest
import play.api.test.Helpers
import play.api.test.FakeHeaders
import org.specs2.mock.Mockito
import scala.concurrent.Future
import play.api.libs.ws.Response
import play.api.test.TestServer
import scala.concurrent._
import scala.concurrent.duration.Duration
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification
import play.api.test.WithApplication

@RunWith(classOf[JUnitRunner])
class PaymentControllerUnitTestWithMockito extends Specification with Mockito {

  val amount = 198.23
  val sender = "borko@gmail.com"
  val recipient = "stanska@gmail.com"
  val currency = "USD"
  val statusUrl = "statusUrl"

  "PaymentController" should {
//    "when add by request should transform parameters and construct response" in new WithApplication {
//      val skrillJson = Json.obj(
//        "amount" -> amount,
//        "recipient" -> recipient,
//        "sender" -> sender,
//        "currency" -> currency,
//        "ewallet" -> EwalletOption.skrill.toString(),
//        "statusUrl" -> statusUrl)
//
//      val paymentService = mock[PaymentService]
//      paymentService.getPayToForm(new CreatePaymentRequest(amount,
//        sender,
//        recipient,
//        currency,
//        EwalletOption.skrill,
//        statusUrl), EwalletOption.url(EwalletOption.skrill)) returns new EwalletHtmlResponse(200, "Payment to stanska@gmail.com 198.23&nbsp;USD borko@gmail.com")
//
//      val controller = new PaymentController(paymentService)
//      var request = FakeRequest(Helpers.POST, "payment", FakeHeaders(Seq(("Content-type", Seq("application/json")))), skrillJson).withJsonBody(skrillJson);
//      val result = controller.add(request)
//      assert(status(result) == 200)
//      assert(contentType(result).get == "text/html")
//      assert(charset(result).get == "utf-8")
//      assert(contentAsString(result).contains("stanska@gmail.com"))
//      assert(contentAsString(result).contains("198.23"))
//      assert(contentAsString(result).contains("USD"))
//      assert(contentAsString(result).contains("borko@gmail.com"))
//    }
  }
  //  
  //  @Test def testProxyMock = {
  //    val skrillJson = Json.obj(
  //      "amount" -> amount,
  //      "recipient" -> recipient,
  //      "sender" -> sender,
  //      "currency" -> currency,
  //      "ewallet" -> EwalletOption.skrill.toString())
  //
  //    val paymentService = mock[PaymentService]
  //    paymentService.getPayToForm(new CreatePaymentRequest(amount,  
  //                     sender, 
  //                     recipient, 
  //                     currency, 
  //                     EwalletOption.skrill), EwalletOption.url(EwalletOption.skrill)) returns new EwalletHtmlResponse(200, "Payment to stanska@gmail.com 198.23&nbsp;USD borko@gmail.com")
  //    
  //    val controller = new PaymentController(paymentService)
  //    var request = FakeRequest(Helpers.POST, "payment", FakeHeaders(Seq(("Content-type", Seq("application/json")))), skrillJson).withJsonBody(skrillJson);
  //    val result = controller.add(request)
  //    assert(status(result) == 200)
  //    assert(contentType(result).get == "text/plain")
  //    assert(charset(result).get == "utf-8")
  //    assert(contentAsString(result).contains("Payment to stanska@gmail.com"))
  //    assert(contentAsString(result).contains("198.23&nbsp;USD"))
  //    assert(contentAsString(result).contains("borko@gmail.com"))
  //  }
}