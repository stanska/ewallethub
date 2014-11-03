package analytics

import payment.Payment
import payment.PaymentRepository

object PaymentAnalytics {
	def getPaymentsForRecipient(recipient:String):Iterator[Payment] = {
	  PaymentRepository.getPaymentsForRecipient(recipient)
	}
}