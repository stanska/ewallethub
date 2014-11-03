package notification

import play.api.libs.concurrent.Akka
import play.api.Play.current
import akka.actor.Actor
import akka.actor.Props

case object NotificationFailed
case object NotificationSuccessful
class NotificatorManager extends Actor {
  
  def receive = {
    case notification: Notification =>
      {
        val notificator = Akka.system.actorOf(Notificator.props(self, new WebServiceProxy))
        notificator ! notification
      }
  }
}