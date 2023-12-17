package controllers

import com.rallyhealth.weejson.v1.jackson.{FromJson, ToJson}
import com.rallyhealth.weepickle.v1.WeePickle.{FromScala, ToScala}
import models.{UpdateEvolutionsRequest, UpdateEvolutionsResponse}
import play.api.Logger
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, Action, ControllerComponents}
import services.EvolutionService

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

class EvolutionController(
  evolutionService: EvolutionService,
  cc: ControllerComponents
)(implicit
  ec: ExecutionContext,
  val logger: Logger
) extends AbstractController(cc) {

  def updateEvolutions(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    FromJson(request.body.toString).validate[UpdateEvolutionsRequest](ToScala[UpdateEvolutionsRequest]) match {
      case Success(updateEvolutionsRequest) =>
        evolutionService
          .updateEvolutions(
            maybePokemonName = updateEvolutionsRequest.pokemonName
          )
          .map { result =>
            Ok(
              FromScala(
                UpdateEvolutionsResponse(
                  pokemonName = updateEvolutionsRequest.pokemonName,
                  success = result.success,
                  updateSize = result.updateSize
                )
              ).transform(ToJson.string)
            ).as(JSON)
          }
      case _ =>
        Future.successful(BadRequest)
    }
  }

}
