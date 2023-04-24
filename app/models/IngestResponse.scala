package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroFrom, From}

final case class IngestResponse(
  success: Boolean,
  operation: IngestOperation
)

object IngestResponse {

  implicit val encoder: From[IngestResponse] = macroFrom[IngestResponse]

}
