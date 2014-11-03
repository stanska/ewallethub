package payment

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PaymentStatusTest extends Specification {
  "PaymentStatus" should {
    "return processed status for 2 skrill status" in {
      PaymentStatus.fromParameterStatus("2", EwalletOption.skrill) must equalTo(PaymentStatus.PROCESSED)
    }
    "return null status for option paypal" in {
      PaymentStatus.fromParameterStatus("2", EwalletOption.paypal) must equalTo(null)
    }
    "return null status for option none" in {
      PaymentStatus.fromParameterStatus("2", EwalletOption.none) must equalTo(null)
    }
    "return failed for skrill status -2" in {
    	PaymentStatus.fromParameterStatus("-2", EwalletOption.skrill) must equalTo(PaymentStatus.FAILED)
    }
    "return cancelled for skrill status -1" in {
      PaymentStatus.fromParameterStatus("-1", EwalletOption.skrill) must equalTo(PaymentStatus.CANCELLED)
    }

    "return temporary for skrill status 0" in {
      PaymentStatus.fromParameterStatus("0", EwalletOption.skrill) must equalTo(PaymentStatus.TEMPORARY)
    }

    "return chargeback for skrill status -3" in {
      PaymentStatus.fromParameterStatus("-3", EwalletOption.skrill) must equalTo(PaymentStatus.CHARGEBACK)
    }

  }
}