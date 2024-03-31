package modules

import clients.MongoDbClient
import com.softwaremill.macwire.wire

import scala.concurrent.ExecutionContext

trait ClientModule {

  implicit def ec: ExecutionContext

  lazy val db: MongoDbClient = wire[MongoDbClient]

}
