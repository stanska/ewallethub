package notification

import org.specs2.mutable.Specification
import play.api.libs.ws.WS
import payment.EwalletOption._
import payment.EwalletOption
import java.net.HttpURLConnection


class NotificationFixture extends Specification {
  var integratorStatusUrl: String = null
  def setIntegratorStatusUrl(integratorStatusUrlIn: String) = { integratorStatusUrl = integratorStatusUrlIn }
  var paymentReference: String = null
  def setPaymentReference(paymentReferenceIn: String) = { paymentReference = paymentReferenceIn }
  var statusNotification: String = null
  def setStatusNotification(statusNotificationIn: String) = { statusNotification = statusNotificationIn }
  var paymentId: String = null
  def response(): String = {
    val amount = "2"
       val connection:HttpURLConnection = null // TODO: make payment from fitnesse
    //paymentId = payment.PaymentControllerTestHelper.makePaymentReturnId(amount, "stanska@gmail.com", "borko@gmail.com", "USD", "skrill", "http://locahost:9000/test", "reference")
    var request = WS.url("http://localhost:9000/payment").post(Map("amount" -> Seq(amount.toString),
      "pay_from_email" -> Seq("borko@gmail.com"),
      "pay_to_email" -> Seq("stanska@gmail.com")))
    //FakeRequest(Helpers.POST, "notification", FakeHeaders(), ()).withFormUrlEncodedBody(("amount", amount),
    //  ("pay_from_email", "borko@gmaiactor"), ("ewallet_option", "skrill"), ("transaction_id", "123143165"), ("ewallet_hub_transaction_id", paymentId),
    //  ("currency", "EUR"), ("status", "0"));
    //val result = notification.NotificationController.add(request)
    request.toString()
  }
  def storedNotification(): String = ""
  def integratorNotificationResponse(): String = ""

}