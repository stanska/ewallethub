package notification

import payment.EwalletOption
import payment.EwalletOption._
import payment.PaymentStatus
import payment.PaymentStatus._

object NotificationDataFactory {
  def newInstance(parameters: Map[String, Seq[String]]): Notification = {
    val optionString = param("ewallet_option", parameters)
    if (optionString == null) {
      return null
    }
    val option = EwalletOption.withName(optionString)
    Notification(
      option,
      param("ewallet_hub_transaction_id", parameters),
      param("transaction_id", parameters),
      param("amount", parameters),
      param("currency", parameters),
      fromParameterStatus(param("status", parameters), option),
      param("pay_from_email", parameters))
  }

  /**
   * Gets the value of a specified key(field) in the request
   * @param field - key in the request 
   * @param request - representation of the request as key, value map
   */
  private def param(field: String, request: Map[String, Seq[String]]): String = {
    if (request.get(field).flatMap(_.headOption) == None) {
      return null
    }
    request.get(field).flatMap(_.headOption).get
  }

}