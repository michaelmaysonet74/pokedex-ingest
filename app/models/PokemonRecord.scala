package models

import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.bson.ObjectId

final case class PokemonRecord(
  _id: ObjectId,
  id: Int,
  name: Option[String] = None,
  entry: Option[String] = None,
  category: Option[String] = None,
  types: Option[List[String]] = None,
  measurement: Option[Measurement] = None,
  abilities: Option[List[Ability]] = None,
  sprite: Option[String] = None,
  evolution: Option[EvolutionChain] = None,
  isMonoType: Boolean = false,
  weaknesses: Option[List[String]] = None,
  generation: Option[Int] = None,
  baseStats: Option[BaseStats] = None
)

object PokemonRecord {

  def apply(
    id: Int,
    name: Option[String],
    entry: Option[String],
    category: Option[String],
    types: Option[List[String]],
    measurement: Option[Measurement],
    abilities: Option[List[Ability]],
    sprite: Option[String],
    evolution: Option[EvolutionChain],
    isMonoType: Option[Boolean],
    weaknesses: Option[List[String]],
    baseStats: Option[BaseStats]
  ): PokemonRecord =
    PokemonRecord(
      _id = new ObjectId(),
      id = id,
      name = name,
      entry = entry,
      category = category,
      types = types,
      measurement = measurement,
      abilities = abilities,
      sprite = sprite,
      evolution = evolution,
      isMonoType = isMonoType.getOrElse(false),
      weaknesses = weaknesses,
      generation = getGeneration(id),
      baseStats = baseStats
    )

  def apply(
    id: Int,
    baseStats: Option[BaseStats]
  ): PokemonRecord =
    PokemonRecord(
      _id = new ObjectId(),
      id = id,
      baseStats = baseStats
    )

  private def getGeneration(pokemonId: Int): Option[Int] =
    pokemonId match {
      case id if 1 to 151 contains id    => Some(1)
      case id if 152 to 251 contains id  => Some(2)
      case id if 252 to 386 contains id  => Some(3)
      case id if 387 to 493 contains id  => Some(4)
      case id if 494 to 649 contains id  => Some(5)
      case id if 650 to 721 contains id  => Some(6)
      case id if 722 to 809 contains id  => Some(7)
      case id if 810 to 905 contains id  => Some(8)
      case id if 906 to 1025 contains id => Some(9)
      case _                             => None
    }

  val codecRegistry: CodecRegistry =
    fromRegistries(
      fromProviders(
        classOf[PokemonRecord],
        classOf[PokemonType],
        classOf[Measurement],
        classOf[Ability],
        classOf[EvolutionChain],
        classOf[Evolution],
        classOf[BaseStats]
      ),
      DEFAULT_CODEC_REGISTRY
    )

}
