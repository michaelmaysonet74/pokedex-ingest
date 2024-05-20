package models

import clients.schemas.PokedexSchema
import enumeratum._

sealed abstract class PokemonType(val value: String) extends EnumEntry

object PokemonType extends Enum[PokemonType] {

  override def values: IndexedSeq[PokemonType] = findValues

  case object Bug extends PokemonType("Bug")
  case object Dark extends PokemonType("Dark")
  case object Dragon extends PokemonType("Dragon")
  case object Electric extends PokemonType("Electric")
  case object Fairy extends PokemonType("Fairy")
  case object Fighting extends PokemonType("Fighting")
  case object Fire extends PokemonType("Fire")
  case object Flying extends PokemonType("Flying")
  case object Ghost extends PokemonType("Ghost")
  case object Grass extends PokemonType("Grass")
  case object Ground extends PokemonType("Ground")
  case object Ice extends PokemonType("Ice")
  case object Normal extends PokemonType("Normal")
  case object Poison extends PokemonType("Poison")
  case object Psychic extends PokemonType("Psychic")
  case object Rock extends PokemonType("Rock")
  case object Steel extends PokemonType("Steel")
  case object Water extends PokemonType("Water")

  def convert(pokemonType: PokedexSchema.PokemonType): PokemonType =
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
