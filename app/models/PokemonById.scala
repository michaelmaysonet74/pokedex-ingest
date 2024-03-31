package models

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

final case class PokemonBaseStatsById(
  id: String,
  baseStats: Option[BaseStats]
)
