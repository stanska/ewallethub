package payment

import play.api.Play

object EwalletOption extends Enumeration {
  type EwalletOption = Value
  val paypal, skrill, none = Value

  def url(option: EwalletOption): String = option match {
    case `paypal` => "https://www.sandbox.paypal.com/cgi-bin/webscr"
    case `skrill` => "https://www.moneybookers.com/app/payment.pl"
  }

  def parameterMap(option: EwalletOption, paymentRequest: CreatePaymentRequest, transactionId:String): Map[String, Seq[String]] = option match {
    case `skrill` => Map("amount" -> Seq(paymentRequest.amount.toString),
      "pay_from_email" -> Seq(paymentRequest.sender),
      "pay_to_email" -> Seq(paymentRequest.recipient),
      "currency" -> Seq(paymentRequest.currency),
      "merchant_fields" -> Seq("ewallet_hub_transaction_id"),
      "ewallet_hub_transaction_id" -> Seq(transactionId),
      "transaction_id" -> Seq(paymentRequest.reference),
      "status_url" -> Seq(Play.current.configuration.getString("application.baseUrl").get + "/notification")
      )
    case `paypal` => Map("cmd" -> Seq("_xclick"),
      "amount" -> Seq(paymentRequest.amount.toString),
      "email" -> Seq(paymentRequest.sender),
      "business" -> Seq(paymentRequest.recipient),
      "currency" -> Seq(paymentRequest.currency),
      "ewallet_hub_transaction_id" -> Seq(transactionId),
      "notify_url" -> Seq(Play.current.configuration.getString("application.baseUrl").get + "/notification"))
  }

  def ewalletOptionByString(option: String): EwalletOption = {
    try {
      EwalletOption.withName(option)
    } catch {
      case _: NoSuchElementException => none
    }
  }
}
