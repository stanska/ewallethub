package controllers

import play.api.mvc.Action
import play.api.mvc.Controller

object TestController extends Controller {
	def test = {
	  Action {
		  implicit request => {
		    //Ok(	request.body.toString() )
		    Ok("OK") 
		  }
	  }
	}
}