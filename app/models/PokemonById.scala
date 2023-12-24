package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroTo, To}

final case class PokemonById(
  id: String,
  name: Option[String],
  entry: Option[String],
  category: Option[String],
  types: Option[List[Option[PokemonType]]],
  measurement: Option[Measurement],
  abilities: Option[List[Option[Ability]]],
  sprite: Option[String],
  evolution: Option[EvolutionChain],
  isMonoType: Option[Boolean],
  weaknesses: Option[List[Option[PokemonType]]],
  baseStats: Option[BaseStats]
)

object PokemonById {

  implicit val decoder: To[PokemonById] = macroTo[PokemonById]

  def apply(
    id: String,
    baseStats: Option[BaseStats]
  ): PokemonById = PokemonById(
    id = id,
    name = None,
    entry = None,
    category = None,
    types = None,
    measurement = None,
    abilities = None,
    sprite = None,
    evolution = None,
    isMonoType = None,
    weaknesses = None,
    baseStats = baseStats
  )

}
