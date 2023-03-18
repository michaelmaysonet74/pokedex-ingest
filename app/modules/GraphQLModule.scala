package modules

import akka.stream.Materializer
import com.softwaremill.macwire.wire
import controllers.GraphQLController

trait GraphQLModule extends TemplateModule {

  implicit def mat: Materializer

  lazy val graphQLController: GraphQLController = wire[GraphQLController]

}
