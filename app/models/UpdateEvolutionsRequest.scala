package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroTo, To}

case class UpdateEvolutionsRequest(
  pokemonName: Option[String]
)

object UpdateEvolutionsRequest {

  implicit val decoder: To[UpdateEvolutionsRequest] = macroTo[UpdateEvolutionsRequest]

}
