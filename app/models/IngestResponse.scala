package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroFrom, From}

final case class IngestResponse(
  success: Boolean,
  count: Int,
  operation: IngestOperation
)

object IngestResponse {

  implicit val encoder: From[IngestResponse] = macroFrom[IngestResponse]

}
