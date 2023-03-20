package modules

import clients.MongoDbClient
import com.softwaremill.macwire.wire
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext

trait ClientModule {

  implicit def ec: ExecutionContext

  def wsClient: WSClient

  lazy val db: MongoDbClient = wire[MongoDbClient]

}
