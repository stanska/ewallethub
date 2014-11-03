package notification

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.test._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.mvc.MultipartFormData
import play.api.mvc.MultipartFormData._
import payment.PaymentController
@RunWith(classOf[JUnitRunner])
class NotificationControllerTest extends Specification {
  val amount = "198.334"
  "Notification for a new skrill payment " should {
    "return Done(skrill, ewallet hub transaction id, reference, amount = " + amount + ", currency, status, sender)," +
    "record notification details,\n" +
    "update payment,\n" + 
    "post report" in new WithApplication {
     var paymentResult = payment.PaymentControllerTestHelper.makePayment(amount, "stanska@gmail.com","borko@gmail.com","USD","skrill","http://locahost:9000/test", "reference")
     val paymentResultString = contentAsString(paymentResult)
     
     val paymentIdTmp = paymentResultString.substring(paymentResultString.indexOf("ewallet_hub_transaction_id\" value=\"")+"ewallet_hub_transaction_id\" value=\"".length, paymentResultString.length()-1)
     val paymentId = paymentIdTmp.substring(0, paymentIdTmp.indexOf("\""))
     
      var request = FakeRequest(Helpers.POST, "notification", FakeHeaders(), ()).withFormUrlEncodedBody(("amount", amount),
        ("pay_from_email", "borko@gmaiactor"), ("ewallet_option", "skrill"), ("transaction_id", "123143165"), ("ewallet_hub_transaction_id", paymentId),
        ("currency", "EUR"), ("status", "0"));
      val result = notification.NotificationController.add(request)
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/plain")
      charset(result) must beSome("utf-8")
      contentAsString(result) must equalTo("DoneNotification(skrill,"+paymentId+",123143165," + amount + ",EUR,TEMPORARY,borko@gmaiactor)")
      
      //check payment report in db
        
      //check notification to specific url response
    }
    //same for paypal  
  }
}








