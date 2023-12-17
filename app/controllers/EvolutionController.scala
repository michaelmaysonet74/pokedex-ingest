package controllers

import com.rallyhealth.weejson.v1.jackson.ToJson
import com.rallyhealth.weepickle.v1.WeePickle.FromScala
import models.UpdateEvolutionsResponse
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

  def updateEvolutions(): Action[AnyContent] =
    Action.async {
      evolutionService
        .updateEvolutions()
        .map { result =>
          Ok(
            FromScala(
              UpdateEvolutionsResponse(
                success = result.success,
                updateSize = result.updateSize
              )
            ).transform(ToJson.string)
          ).as(JSON)
        }
    }

}
