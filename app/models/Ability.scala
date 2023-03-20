package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroTo, To}

final case class Ability(
  name: Option[String],
  effect: Option[String],
  isHidden: Option[Boolean]
)

object Ability {

  implicit val decoder: To[Ability] = macroTo[Ability]

}
