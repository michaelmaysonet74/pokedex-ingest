package models

import com.rallyhealth.weepickle.v1.WeePickle.{fromTo, FromTo}
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

  implicit val decoder: FromTo[PokemonType] = fromTo[String].bimap[PokemonType](_.value, withNameInsensitive)

}
