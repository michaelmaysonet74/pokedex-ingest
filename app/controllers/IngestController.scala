package controllers

import com.rallyhealth.weejson.v1.jackson.{FromJson, ToJson}
import com.rallyhealth.weepickle.v1.WeePickle.{FromScala, ToScala}
import models.{IngestOperation, IngestRequest, IngestResponse}
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

  private val BATCH_SIZE_LIMIT = 100

  def ingest(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    FromJson(request.body.toString).validate[IngestRequest](ToScala[IngestRequest]) match {
      case Success(ingestRequest) =>
        if (ingestRequest.endPokemonId.getOrElse(0) - ingestRequest.startPokemonId > BATCH_SIZE_LIMIT)
          Future.successful(
            BadRequest(
              s"The range from startPokemonId to endPokemonId exceeds the limit of $BATCH_SIZE_LIMIT in total."
            )
          )
        else
          ingestService
            .ingest(
              start = ingestRequest.startPokemonId,
              end = ingestRequest.endPokemonId.getOrElse(ingestRequest.startPokemonId),
              operation = ingestRequest.operation
            )
            .map { result =>
              Ok(
                FromScala(
                  IngestResponse(
                    success = result.success,
                    count = result.count,
                    operation = ingestRequest.operation.getOrElse(IngestOperation.InsertAll)
                  )
                ).transform(ToJson.string)
              ).as(JSON)
            }
      case _ =>
        Future.successful(BadRequest)
    }
  }

}
