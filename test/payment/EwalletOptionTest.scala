package payment

import org.specs2.mutable.Specification
import play.api.test.WithApplication
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EwalletOptionTest extends Specification {
  val amount = 198.23
  val sender = "borko@gmail.com"
  val recipient = "stanska@gmail.com"
  val currency = "USD"
  val statusUrl = "statusUrl"
  val reference = "123"
  val ewallet_hub_transaction_id = "1273"
  "EwalletOption" should {
    "return skrill specific parameters and url" in new WithApplication {
      val parameterMap = EwalletOption.parameterMap(EwalletOption.skrill, new CreatePaymentRequest(amount, sender, recipient, currency, EwalletOption.skrill, statusUrl, reference), ewallet_hub_transaction_id)
      parameterMap("amount").iterator.next must equalTo(amount.toString)
      parameterMap("pay_to_email").iterator.next must equalTo(recipient)
      parameterMap("pay_from_email").iterator.next must equalTo(sender)
      parameterMap("currency").iterator.next must equalTo(currency)
      parameterMap("status_url").iterator.next equals("http://tralala:9000/notifications")
      parameterMap("merchant_fields").iterator.next.equals("ewallet_hub_transaction_id")
      parameterMap("ewallet_hub_transaction_id").iterator.next.equals(ewallet_hub_transaction_id)
      parameterMap("transaction_id").iterator.next.equals(reference)
      val url = EwalletOption.url(EwalletOption.skrill)
      url must equalTo("https://www.moneybookers.com/app/payment.pl")
    }

    "return paypall specific parameters and url" in new WithApplication {
      val parameterMap = EwalletOption.parameterMap(EwalletOption.paypal, new CreatePaymentRequest(amount, sender, recipient, currency, EwalletOption.paypal, statusUrl, reference), ewallet_hub_transaction_id)
      parameterMap("amount").iterator.next must equalTo(amount.toString)
      parameterMap("business").iterator.next must equalTo(recipient)
      parameterMap("email").iterator.next must equalTo(sender)
      parameterMap("currency").iterator.next must equalTo(currency)
      parameterMap("notify_url").iterator.next equals("http://tralala:9000/notifications")
      
      val url = EwalletOption.url(EwalletOption.paypal)
      url must equalTo("https://www.sandbox.paypal.com/cgi-bin/webscr")
    }
  }
}