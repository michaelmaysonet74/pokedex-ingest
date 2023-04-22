package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroTo, To}

final case class BaseStats(
  hp: Int,
  attack: Int,
  defense: Int,
  specialAttack: Int,
  specialDefense: Int,
  speed: Int
)

object BaseStats {

  implicit val decoder: To[BaseStats] = macroTo[BaseStats]

}
