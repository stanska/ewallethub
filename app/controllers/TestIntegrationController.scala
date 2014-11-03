package controllers

import play.api.mvc.Action
import play.api.mvc.Controller
import payment.EwalletOption

object TestIntegrationController extends Controller {
	def test = {
	  Action {
		  implicit request => {
          Ok(views.html.testPayment.render(request.host));
		  }
	  }
	}
}