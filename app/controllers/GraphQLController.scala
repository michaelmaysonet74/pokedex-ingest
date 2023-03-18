package controllers

import akka.stream.Materializer
import caliban.PlayAdapter
import graphql.schemas.TemplateSchema
import play.api.routing.{Router, SimpleRouter}
import zio.Unsafe

import scala.concurrent.ExecutionContext
import scala.util.Success

class GraphQLController(
  templateSchema: TemplateSchema
)(
  implicit ec: ExecutionContext,
  implicit val mat: Materializer
) extends SimpleRouter {

  private implicit val runtime: zio.Runtime[Any] = zio.Runtime.default

  private val api = templateSchema.api

  def routes: Router.Routes = {
    val maybeRoutes = Unsafe
      .unsafe { implicit unsafe =>
        runtime.unsafe.runToFuture {
          api.interpreter.map { interpreter =>
            PlayAdapter.makeHttpService(interpreter)
          }
        }
      }
      .future
      .value

    maybeRoutes match {
      case Some(Success(routes)) => routes
      case _ =>
        throw new RuntimeException("Unable to load GraphQL route")
    }

  }

}
