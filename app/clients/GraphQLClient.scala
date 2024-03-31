package clients

import caliban.client.Operations.RootQuery
import caliban.client.SelectionBuilder
import sttp.client3.{HttpClientFutureBackend, UriContext}

import scala.concurrent.{ExecutionContext, Future}

trait GraphQLClient {

  implicit def ec: ExecutionContext

  def execute[T](
    query: SelectionBuilder[RootQuery, Option[T]]
  )(implicit
    uri: String
  ): Future[Option[T]] =
    query
      .toRequest(uri"$uri")
      .send(HttpClientFutureBackend())
      .map { response =>
        response.body match {
          case Right(result) => result
          case _             => None
        }
      }

}
