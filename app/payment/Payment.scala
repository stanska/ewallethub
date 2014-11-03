package payment

import payment.EwalletOption.EwalletOption
import java.util.Date
import notification.Notification
object PaymentTimestampFormat {
  val timestampFormat = new java.text.SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy")

  def format(timestamp: String): Date = {
	  timestampFormat.parse(timestamp)
  }
}
case class Payment(

  val id: String,
  val amount: BigDecimal,
  val sender: String,
  val recipient: String,
  val currency: String,
  val ewalletOption: EwalletOption,
  val remoteIp: String,
  val statusUrl: String,
  val timestamp: Date,
  val reference: String) {
  def this(request: CreatePaymentRequest, id: String, ip: String, statusUrl: String, timestamp: Date, reference: String) = this(id, request.amount, request.sender, request.recipient, request.currency, request.ewalletOption, ip, statusUrl, timestamp, reference)
}