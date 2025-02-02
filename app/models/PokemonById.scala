package models

import caliban.client.SelectionBuilder
import clients.schemas.PokedexSchema

final case class PokemonById(
  id: String,
  name: Option[String],
  entry: Option[String],
  category: Option[String],
  types: Option[PokemonTypes],
  measurement: Option[Measurement],
  abilities: Option[List[Option[Ability]]],
  sprite: Option[String],
  evolution: Option[EvolutionChain],
  isMonoType: Option[Boolean],
  weaknesses: Option[List[Option[PokemonType]]],
  baseStats: Option[BaseStats]
)

object PokemonById {

  val fragment: SelectionBuilder[PokedexSchema.Pokemon, PokemonById] = (
    PokedexSchema.Pokemon.id ~
      PokedexSchema.Pokemon.name ~
      PokedexSchema.Pokemon.entry() ~
      PokedexSchema.Pokemon.category() ~
      PokedexSchema.Pokemon.types {
        PokemonTypes.fragment
      } ~
      PokedexSchema.Pokemon.measurement {
        Measurement.fragment
      } ~
      PokedexSchema.Pokemon.abilities {
        Ability.fragment
      } ~
      PokedexSchema.Pokemon.sprite ~
      PokedexSchema.Pokemon.evolution {
        EvolutionChain.fragment
      } ~
      PokedexSchema.Pokemon.isMonoType ~
      PokedexSchema.Pokemon.weaknesses ~
      PokedexSchema.Pokemon.baseStats {
        BaseStats.fragment
      }
  ).map {
    case (
          id,
          name,
          entry,
          category,
          types,
          measurement,
          abilities,
          sprite,
          evolution,
          isMonoType,
          weaknesses,
          baseStats
        ) =>
      PokemonById(
        id = id,
        name = name,
        entry = entry,
        category = category,
        types = types,
        measurement = measurement,
        abilities = abilities,
        sprite = sprite,
        evolution = evolution,
        isMonoType = isMonoType,
        weaknesses = weaknesses.map(_.map(_.map(PokemonType.convert))),
        baseStats = baseStats
      )
  }

}
