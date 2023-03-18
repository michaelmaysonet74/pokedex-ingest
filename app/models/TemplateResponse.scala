package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroFrom, From}

final case class TemplateResponse(
  url: String,
  status: String
)

object TemplateResponse {

  implicit val w: From[TemplateResponse] = macroFrom[TemplateResponse]

}
