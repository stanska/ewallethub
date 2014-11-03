package payment

import payment.EwalletOption.EwalletOption

case class CreatePaymentRequest(amount: BigDecimal,
  sender: String,
  recipient: String,
  currency: String,
  ewalletOption: EwalletOption,
  statusUrl:String,
  reference:String) {

  def this(amountString: String,
    sender: String,
    recipient: String,
    currency: String,
    ewalletOption: EwalletOption,
    statusUrl:String,
    reference:String) = this(BigDecimal.apply(amountString), sender, recipient, currency, ewalletOption, statusUrl, reference)

}

