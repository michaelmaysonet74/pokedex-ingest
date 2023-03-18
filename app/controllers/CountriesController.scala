package controllers

import com.rallyhealth.weejson.v1.jackson.ToJson
import com.rallyhealth.weepickle.v1.WeePickle.FromScala
import play.api.Logger
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import services.CountriesService

import scala.concurrent.ExecutionContext

class CountriesController(
  countriesService: CountriesService,
  cc: ControllerComponents
)(implicit
  ec: ExecutionContext,
  val logger: Logger
) extends AbstractController(cc) {

  def getCountryByCode(code: String): Action[AnyContent] = Action.async {
    countriesService
      .getCountryByCode(code)
      .map {
        case Some(response) => Ok(FromScala(response).transform(ToJson.string)).as(JSON)
        case _              => NotFound
      }
  }

}
