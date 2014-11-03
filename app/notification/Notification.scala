package notification

import payment.EwalletOption._
import payment.PaymentStatus._
import java.util.Calendar

case class Notification(
  val ewallet: EwalletOption,
  val transactionId: String,
  val recipientTransactionId: String,
  val amount: String,
  val currency: String,
  val status: PaymentStatus,
  val senderEmail: String) {
  val calendar = Calendar.getInstance()
  def timestamp = calendar.getTime()
}