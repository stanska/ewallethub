package filters

import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global
import org.slf4j.Logger
import org.slf4j.LoggerFactory

//object LoggingFilter extends Filter {
//  val logger:Logger = LoggerFactory.getLogger(getClass);
//  def apply(next: (RequestHeader) => Result)(rh: RequestHeader) = {
//    val start = System.currentTimeMillis
//    def logTime(result: PlainResult): Result = {
//      val time = System.currentTimeMillis - start
//      logger.info(s"${rh.method} ${rh.uri} took ${time}ms and returned ${result.header.status}, remoteIp=${rh.remoteAddress}")
//      result.withHeaders("Request-Time" -> time.toString)
//    }
//
//    next(rh) match {
//      case plain: PlainResult => logTime(plain)
//      case async: AsyncResult => async.transform(logTime)
//    }
//  }
//}