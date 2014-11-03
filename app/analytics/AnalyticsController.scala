package analytics

import play.api.mvc.Controller
import play.api.mvc.Action

object AnalyticsController extends Controller {
  def get = {
    Action {
      implicit request =>
        {
          val recipient = request.getQueryString("recipient")
          val payments = PaymentAnalytics.getPaymentsForRecipient(recipient.get)
          val parameters = Map("recipient" -> recipient)
          Ok(views.html.analytics.render(parameters, payments))
        }
    }
  }
}