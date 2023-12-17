package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroFrom, From}

final case class UpdateEvolutionsResponse(
  success: Boolean,
  updateSize: Int
)

object UpdateEvolutionsResponse {

  implicit val encoder: From[UpdateEvolutionsResponse] = macroFrom[UpdateEvolutionsResponse]

}
