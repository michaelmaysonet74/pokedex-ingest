package clients

import caliban.client.Operations.RootQuery
import caliban.client.SelectionBuilder
import sttp.client3.{HttpClientFutureBackend, UriContext}
import java.net.http.HttpClient

import scala.concurrent.{ExecutionContext, Future}

trait GraphQLClient {

  implicit def ec: ExecutionContext

  def execute[T](
    query: SelectionBuilder[RootQuery, Option[T]]
  )(implicit
    uri: String
  ): Future[Option[T]] = {
    val client = HttpClient
      .newBuilder()
      .version(HttpClient.Version.HTTP_1_1)
      .build()

    query
      .toRequest(uri"$uri")
      .send(HttpClientFutureBackend.usingClient(client))
      .map { response =>
        response.body match {
          case Right(result) => result
          case _             => None
        }
      }
  }
}
