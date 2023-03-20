package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroTo, To}

final case class PokemonById(
  id: String,
  name: Option[String],
  entry: Option[String],
  types: Option[List[Option[PokemonType]]],
  measurement: Option[Measurement],
  abilities: Option[List[Option[Ability]]],
  sprite: Option[String],
  evolution: Option[EvolutionChain],
  isMonoType: Option[Boolean],
  weaknesses: Option[List[Option[PokemonType]]]
)

object PokemonById {

  implicit val decoder: To[PokemonById] = macroTo[PokemonById]

}
