package models

import caliban.client.SelectionBuilder
import clients.schemas.PokedexSchema

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

  val fragment: SelectionBuilder[PokedexSchema.Pokemon, PokemonById] = (
    PokedexSchema.Pokemon.id ~
      PokedexSchema.Pokemon.name ~
      PokedexSchema.Pokemon.entry() ~
      PokedexSchema.Pokemon.category() ~
      PokedexSchema.Pokemon.types ~
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
        types = types.map(_.map(_.map(convert))),
        measurement = measurement,
        abilities = abilities,
        sprite = sprite,
        evolution = evolution,
        isMonoType = isMonoType,
        weaknesses = weaknesses.map(_.map(_.map(convert))),
        baseStats = baseStats
      )
  }

  private def convert(pokemonType: PokedexSchema.PokemonType): PokemonType =
    pokemonType match {
      case PokedexSchema.PokemonType.Bug      => PokemonType.Bug
      case PokedexSchema.PokemonType.Dark     => PokemonType.Dark
      case PokedexSchema.PokemonType.Dragon   => PokemonType.Dragon
      case PokedexSchema.PokemonType.Electric => PokemonType.Electric
      case PokedexSchema.PokemonType.Fairy    => PokemonType.Fairy
      case PokedexSchema.PokemonType.Fighting => PokemonType.Fighting
      case PokedexSchema.PokemonType.Fire     => PokemonType.Fire
      case PokedexSchema.PokemonType.Flying   => PokemonType.Flying
      case PokedexSchema.PokemonType.Ghost    => PokemonType.Ghost
      case PokedexSchema.PokemonType.Grass    => PokemonType.Grass
      case PokedexSchema.PokemonType.Ground   => PokemonType.Ground
      case PokedexSchema.PokemonType.Ice      => PokemonType.Ice
      case PokedexSchema.PokemonType.Normal   => PokemonType.Normal
      case PokedexSchema.PokemonType.Poison   => PokemonType.Poison
      case PokedexSchema.PokemonType.Psychic  => PokemonType.Psychic
      case PokedexSchema.PokemonType.Rock     => PokemonType.Rock
      case PokedexSchema.PokemonType.Steel    => PokemonType.Steel
      case PokedexSchema.PokemonType.Water    => PokemonType.Water
    }

}
