package services

import clients.TemplateClient

import scala.concurrent.{ExecutionContext, Future}

trait TemplateService {

  def getStatus(url: String): Future[String]

}

class TemplateServiceImpl(
  templateClient: TemplateClient
)(implicit
  ec: ExecutionContext
) extends TemplateService {

  override def getStatus(url: String): Future[String] =
    templateClient.getStatus(url)

}
