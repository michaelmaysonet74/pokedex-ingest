package graphql.schemas

import caliban.GraphQL.graphQL
import caliban.{GraphQL, RootResolver}
import caliban.schema.Annotations.GQLDescription
import graphql.models.TemplateArgs
import models.TemplateResponse
import services.TemplateService

import scala.concurrent.{ExecutionContext, Future}

class TemplateSchema(
  templateService: TemplateService
)(implicit
  ec: ExecutionContext
) extends Schema {

  private case class Queries(
    @GQLDescription("A template description")
    template: TemplateArgs => Future[TemplateResponse]
  )

  private val queries = Queries(
    template = templateResolver
  )

  override def api: GraphQL[Any] = graphQL(RootResolver(queries))

  private def templateResolver(args: TemplateArgs): Future[TemplateResponse] =
    templateService.getStatus(args.url).map(TemplateResponse(args.url, _))

}
