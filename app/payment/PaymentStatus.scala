package payment

import EwalletOption._

object PaymentStatus extends Enumeration {
  type PaymentStatus = Value
  val FAILED, PROCESSED, TEMPORARY, CANCELLED, CHARGEBACK = Value
  def fromParameterStatus(status: String, ewalletOption: EwalletOption): PaymentStatus = {
    ewalletOption match {
      case `skrill` =>
        status match {
          case "2" => PROCESSED
          case "-2" => FAILED
          case "0" => TEMPORARY
          case "-1" => CANCELLED
          case "-3" => CHARGEBACK
          case null => throw new IllegalStateException("Invalid payment status")
        }
      case `none` => null
      case `paypal` => null
    }
  }
}
