package payment

import payment.EwalletOption.EwalletOption
import payment.EwalletOption.none
import play.api.libs.json.JsResultException

object CreatePaymentRequestFactory {
  def newInstance(request: play.api.mvc.Request[play.api.mvc.AnyContent]): CreatePaymentRequest = {
    request.body.asJson match {
      case Some(e) => {
        new CreatePaymentRequest((e \ "amount").as[BigDecimal],
          (e \ "sender").as[String],
          (e \ "recipient").as[String],
          (e \ "currency").as[String],
          getEwalletOption(e),
          (e \ "statusUrl").as[String],
          (e \ "reference").as[String])
      }

      case None =>
        {
          if (!request.body.asMultipartFormData.isEmpty) {
            new CreatePaymentRequest(param("amount", request.body.asMultipartFormData.get.asFormUrlEncoded),
              param("sender", request.body.asMultipartFormData.get.asFormUrlEncoded),
              param("recipient", request.body.asMultipartFormData.get.asFormUrlEncoded),
              param("currency", request.body.asMultipartFormData.get.asFormUrlEncoded),
              EwalletOption.ewalletOptionByString(param("ewallet", request.body.asMultipartFormData.get.asFormUrlEncoded)),
              param("statusUrl", request.body.asMultipartFormData.get.asFormUrlEncoded),
              param("referernce", request.body.asMultipartFormData.get.asFormUrlEncoded))
          } else if (!request.queryString.isEmpty) {
            new CreatePaymentRequest(param("amount", request.queryString),
              param("sender", request.queryString),
              param("recipient", request.queryString),
              param("currency", request.queryString),
              EwalletOption.ewalletOptionByString(param("ewallet", request.queryString)),
              param("statusUrl", request.queryString),
              param("reference", request.queryString))
          } else {
            throw new Exception("Parameters needed!");
          }
        }
    }
  }

  private def param(field: String, request: Map[String, Seq[String]]): String = {
    request.get(field).flatMap(_.headOption).get
  }

  private def getEwalletOption(e: play.api.libs.json.JsValue): EwalletOption = {
    try {
      EwalletOption.ewalletOptionByString((e \ "ewallet").as[String])
    } catch {
      case _: JsResultException => none
    }
  }

}