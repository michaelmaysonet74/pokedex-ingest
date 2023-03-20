package controllers

import com.rallyhealth.weejson.v1.jackson.{FromJson, ToJson}
import com.rallyhealth.weepickle.v1.WeePickle.{FromScala, ToScala}
import models.{IngestRequest, IngestResponse}
import play.api.Logger
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, Action, ControllerComponents}
import services.IngestService

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

class IngestController(
  ingestService: IngestService,
  cc: ControllerComponents
)(implicit
  ec: ExecutionContext,
  val logger: Logger
) extends AbstractController(cc) {

  def ingest(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    FromJson(request.body.toString).validate[IngestRequest](ToScala[IngestRequest]) match {
      case Success(ingestRequest) =>
        ingestService
          .ingest(
            start = ingestRequest.startPokemonId,
            end = ingestRequest.endPokemonId
          )
          .map { success =>
            Ok(FromScala(IngestResponse(success)).transform(ToJson.string)).as(JSON)
          }
      case _ => Future.successful(BadRequest)
    }
  }

}
