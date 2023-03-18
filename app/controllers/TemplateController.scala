package controllers

import com.rallyhealth.weejson.v1.jackson.ToJson
import com.rallyhealth.weepickle.v1.WeePickle.FromScala
import models.TemplateResponse
import services.TemplateService
import play.api.Logger
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.ExecutionContext

class TemplateController(
  templateService: TemplateService,
  cc: ControllerComponents
)(
  implicit ec: ExecutionContext,
  implicit val logger: Logger
) extends AbstractController(cc) {

  def getStatus(url: String): Action[AnyContent] = Action.async {
    logger.info(s"GET /status?url=$url")
    templateService.getStatus(url).map { status =>
      val response = FromScala(TemplateResponse(url, status)).transform(ToJson.string)
      Ok(response).as(JSON)
    }
  }

}
