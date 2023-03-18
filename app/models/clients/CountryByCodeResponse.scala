package models.clients

import com.rallyhealth.weepickle.v1.WeePickle.{macroTo, To}

final case class CountryByCodeResponse(
  data: CountryByCodeData
)

object CountryByCodeResponse {

  implicit val r: To[CountryByCodeResponse] = macroTo[CountryByCodeResponse]

}

final case class CountryByCodeData(
  country: CountryByCode
)

object CountryByCodeData {

  implicit val r: To[CountryByCodeData] = macroTo[CountryByCodeData]

}

final case class CountryByCode(
  name: String,
  emoji: String
)

object CountryByCode {

  implicit val r: To[CountryByCode] = macroTo[CountryByCode]

}
