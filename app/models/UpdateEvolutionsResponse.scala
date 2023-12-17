package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroFrom, From}
import com.rallyhealth.weepickle.v1.implicits.dropDefault

final case class UpdateEvolutionsResponse(
  @dropDefault
  pokemonName: Option[String],
  success: Boolean,
  updateSize: Int
)

object UpdateEvolutionsResponse {

  implicit val encoder: From[UpdateEvolutionsResponse] = macroFrom[UpdateEvolutionsResponse]

}
