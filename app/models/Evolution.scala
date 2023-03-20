package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroTo, To}

final case class Evolution(
  id: Option[String],
  name: Option[String]
)

object Evolution {

  implicit val decoder: To[Evolution] = macroTo[Evolution]

}
