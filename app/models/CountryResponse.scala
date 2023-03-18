package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroFromTo, FromTo}

final case class CountryResponse(
  name: String,
  flag: String
)

object CountryResponse {

  implicit val rw: FromTo[CountryResponse] = macroFromTo[CountryResponse]

}
