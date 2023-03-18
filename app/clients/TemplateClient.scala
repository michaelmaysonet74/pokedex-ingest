package clients

import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

class TemplateClient(
  ws: WSClient
)(implicit
  ec: ExecutionContext
) {

  def getStatus(url: String): Future[String] =
    ws.url(url)
      .get()
      .map(res => s"${res.status} ${res.statusText}")

}
