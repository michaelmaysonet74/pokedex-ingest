package controllers

import com.rallyhealth.weejson.v1.jackson.ToJson
import com.rallyhealth.weepickle.v1.WeePickle.FromScala
import models.{IngestOperation, IngestResponse}
import play.api.Logger
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import services.EvolutionService

import scala.concurrent.ExecutionContext

class EvolutionController(
  evolutionService: EvolutionService,
  cc: ControllerComponents
)(implicit
  ec: ExecutionContext,
  val logger: Logger
) extends AbstractController(cc) {

  // TODO: Finish impl
  def updateEvolutions(): Action[AnyContent] = Action.async {
    evolutionService.updateEvolutions().map { _ =>
      Ok(
        FromScala(
          IngestResponse(
            success = true,
            operation = IngestOperation.InsertAll
          )
        ).transform(ToJson.string)
      ).as(JSON)
    }
  }

}
