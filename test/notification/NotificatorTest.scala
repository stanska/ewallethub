package notification

import org.specs2.mock._
import akka.testkit.TestKit
import akka.actor.ActorSystem
import org.scalatest.BeforeAndAfterAll
import org.scalatest.FunSuite
import akka.testkit.TestProbe
import play.libs.Akka
import payment.EwalletOption._
import akka.actor.Props
import payment.EwalletOption
import payment.PaymentStatus._
import payment.PaymentStatus
import org.scalatest.matchers.ShouldMatchers
import akka.testkit.ImplicitSender
import scala.actors.Actor
import scala.concurrent.duration._
import database.DatabaseConnection
import com.mongodb.casbah.MongoClient
import payment.CreatePaymentRequest
import payment.Payment
import play.api.libs.ws.WS
import payment.PaymentController
import scala.concurrent.Future
import scala.concurrent._
import play.api.mvc.Controller
import play.api.mvc.SimpleResult
import play.api.mvc.ResponseHeader
import play.api.libs.iteratee.Enumerator
import ExecutionContext.Implicits.global
import payment.PaymentRepository
import payment.PaymentFactory

//TODO uncomment schedule in Notificator
class NotificatorTest extends TestKit(ActorSystem("NotificatorTest"))
  with FunSuite
  with BeforeAndAfterAll
  with ShouldMatchers
  with ImplicitSender
  with Mockito {
  override def afterAll(): Unit = {
    system.shutdown()
  }

  test("case1: send status notification and store report status") {
    val payment = makeDummyPayment("http://localhost:9000/test")
    val manager = TestProbe()

    val proxyMock = mock[WebServiceProxy]
    val okResponse: play.api.libs.ws.Response = mock[play.api.libs.ws.Response]
    okResponse.status returns 200
    okResponse.body returns "OK"

    proxyMock.post("http://localhost:9000/test").returns(future { okResponse })
    var notificator = system.actorOf(Notificator.props(manager.ref, proxyMock), name = "notificatorcase1")
    var notification = new Notification(EwalletOption.paypal, payment.id, payment.reference,
      payment.amount.toString,
      payment.currency,
      PaymentStatus.PROCESSED,
      payment.sender)
    notificator ! notification
    manager.expectMsg(NotificationSuccessful)
    manager.expectNoMsg()
    assert(NotificationRegistry.loadNotificationResponseCount(notification.transactionId) == 1)
  }

  test("case4: when wrong url, fail notification and don't rescedule") {
    val payment = makeDummyPayment("status url")
    val manager = TestProbe()
    var notificator = system.actorOf(Notificator.props(manager.ref, new WebServiceProxy), name = "notificatorcase4")
    var notification = new Notification(EwalletOption.paypal, payment.id, payment.reference,
      payment.amount.toString,
      payment.currency,
      PaymentStatus.PROCESSED,
      payment.sender)
    notificator ! notification
    manager.expectMsg(NotificationFailed)
    manager.expectNoMsg()
    assert(NotificationRegistry.loadNotificationResponseCount(notification.transactionId) == 1)
  }

  test("case3: when correct url but different than ok response, fail notification and rescedule") {
    val payment = makeDummyPayment("http://localhost:8080")
    val manager = TestProbe()
    val wsMock = mock[WebServiceProxy]
    WS.url(payment.statusUrl).post(Map[String, Seq[String]]())
    var notificator = system.actorOf(Notificator.props(manager.ref, new WebServiceProxy), name = "notificatorcase3")
    var notification = new Notification(EwalletOption.paypal, payment.id, payment.reference,
      payment.amount.toString,
      payment.currency,
      PaymentStatus.PROCESSED,
      payment.sender)
    notificator ! notification
    manager.expectMsg(NotificationFailed)
    manager.expectMsg(notification)
    assert(NotificationRegistry.loadNotificationResponseCount(notification.transactionId) == 1)
  }

  def makeDummyPayment(statusUrl: String): Payment = {
    DatabaseConnection.setMongoClient(MongoClient("localhost", 27017))
    PaymentFactory.add(new CreatePaymentRequest("12.00",
      "sender email",
      "recipient email",
      "EUR",
      EwalletOption.skrill,
      statusUrl, "reference"), "test ip")
  }
}


