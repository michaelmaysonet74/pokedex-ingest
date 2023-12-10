package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroTo, To}

final case class EvolutionChain(
  from: Option[Evolution],
  to: Option[Seq[Evolution]] = None
)

object EvolutionChain {

  implicit val decoder: To[EvolutionChain] = macroTo[EvolutionChain]

}

final case class Evolution(
  id: Option[String],
  name: Option[String]
)

object Evolution {

  implicit val decoder: To[Evolution] = macroTo[Evolution]

}
