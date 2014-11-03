package payment

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import payment.EwalletOption._
import play.api.libs.ws.WS
import play.api.mvc.Action
import play.api.mvc.Controller
import notification.NotificationRegistry

class EwalletHtmlResponse(val status: Int, val body: String)

class PaymentService {
//  def getPayToForm(paymentRequest: CreatePaymentRequest, url: String): EwalletHtmlResponse = {
//    val future = WS.url(url).post(EwalletOption.parameterMap(paymentRequest.ewalletOption, paymentRequest))
//    val responseBody = Await.result(future, Duration(40000, "millis")).body;
//    new EwalletHtmlResponse(200, responseBody)
//  }
}

class PaymentController(service: PaymentService) extends Controller {

  def add = Action {
    implicit request =>
      val paymentRequest = CreatePaymentRequestFactory.newInstance(request)
      val payment = PaymentFactory.add(paymentRequest, request.remoteAddress)
      if (paymentRequest.ewalletOption == none) {
        Ok("Not supported ewallet option")
      } else {
          val url = EwalletOption.url(paymentRequest.ewalletOption)
          val parameters = EwalletOption.parameterMap(paymentRequest.ewalletOption, paymentRequest, payment.id);
         // Ok(service.getPayToForm(paymentRequest, url).body)
          Ok(views.html.postPayment.render(url,
                                           parameters));
      }
  }
}

object PaymentController extends PaymentController(new PaymentService()) {}