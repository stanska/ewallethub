package notification

import play.api.mvc.Controller
import play.api.mvc.Action
import java.util.Calendar
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import play.api.libs.concurrent.Akka
import play.api.Play.current

object NotificationController extends Controller {

  val actor = Akka.system.actorOf(Props[NotificatorManager])
  def add = {
    Action {
      implicit request =>
        {
          val parameters: Map[String, Seq[String]] = getMapByRequest(request);
          val notificationData = NotificationDataFactory.newInstance(parameters);
          NotificationRegistry.log(notificationData)
          actor ! notificationData
          Ok("Done" + notificationData)
        }
    }
  }

  private def getMapByRequest(request: play.api.mvc.Request[play.api.mvc.AnyContent]): Map[String, Seq[String]] = {
    if (!request.body.asMultipartFormData.isEmpty) {
      request.body.asMultipartFormData.get.asFormUrlEncoded
    } else if (!request.queryString.isEmpty) {
      request.queryString
    } else if (!request.body.asFormUrlEncoded.isEmpty) {
      request.body.asFormUrlEncoded.get
    } else {
      throw new IllegalArgumentException("Invalid Request")
    }
  }

}