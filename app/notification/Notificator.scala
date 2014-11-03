package notification

import akka.actor.Actor
import akka.actor.Props
import play.api.libs.ws.WS
import scala.util.Success
import scala.util.Failure
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import akka.actor.ActorRef
import play.Logger
import payment.PaymentRepository

class WebServiceProxy {
  def post(url:String) = WS.url(url).post(Map[String, Seq[String]]())
}

object Notificator {
   def props(manager: ActorRef, proxy:WebServiceProxy): Props = Props(new Notificator(manager, proxy))
}

class Notificator(val manager:ActorRef, proxy:WebServiceProxy) extends Actor{
  def receive = {
    case notification: Notification =>
      {
        val payment = PaymentRepository.load(notification.transactionId)
        try {
          val future = proxy.post(payment.statusUrl)
          future.onFailure {
            case t => {
              rescheduleNotification
              notificationFailed(t.getMessage())
            }
          }
          future.onComplete {
            case Success(response) =>
              if (response.body != "OK") {
                rescheduleNotification
                notificationFailed(response.body)
                manager ! NotificationFailed
              } else {
            	  successfulNotification(response.body)
            	  manager ! NotificationSuccessful
              }
            case Failure(t) => {
              rescheduleNotification
              Logger.error(t.getMessage())
              notificationFailed(t.getMessage())
              manager ! NotificationFailed
            }
          }
        } catch {
          case t: Exception => {
            notificationFailed(t.getMessage())
            Logger.error(t.getMessage())
            manager ! NotificationFailed
          }
        }
        def successfulNotification(response: String) = {
          NotificationRegistry.logNotificationResponse(notification, payment.statusUrl, response)
        }

        def notificationFailed(errorResponse: String) = {
          NotificationRegistry.logNotificationResponse(notification, payment.statusUrl, errorResponse)
        }

        def rescheduleNotification = {
          context.system.scheduler.scheduleOnce(Duration.create(1, TimeUnit.SECONDS), //for test purposes, otherwise set on 5 mins, smth conf, and increasing depending on the failure rate
            manager, notification)
        }
      }
  }
}


