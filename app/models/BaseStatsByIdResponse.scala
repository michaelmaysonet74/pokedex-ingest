package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroTo, To}

final case class BaseStatsByIdResponse(
  data: BaseStatsByIdData
)

object BaseStatsByIdResponse {

  implicit val decoder: To[BaseStatsByIdResponse] = macroTo[BaseStatsByIdResponse]

}

final case class BaseStatsByIdData(
  id: Int,
  baseStats: BaseStats
)

object BaseStatsByIdData {

  implicit val decoder: To[BaseStatsByIdData] = macroTo[BaseStatsByIdData]

}
