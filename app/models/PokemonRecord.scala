package models

import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.bson.ObjectId

final case class PokemonRecord(
  _id: ObjectId,
  id: Int,
  name: Option[String],
  entry: Option[String],
  types: Option[List[String]],
  measurement: Option[Measurement],
  abilities: Option[List[Ability]],
  sprite: Option[String],
  evolution: Option[EvolutionChain],
  isMonoType: Boolean,
  weaknesses: Option[List[String]]
)

object PokemonRecord {

  def apply(
    id: Int,
    name: Option[String],
    entry: Option[String],
    types: Option[List[String]],
    measurement: Option[Measurement],
    abilities: Option[List[Ability]],
    sprite: Option[String],
    evolution: Option[EvolutionChain],
    isMonoType: Option[Boolean],
    weaknesses: Option[List[String]]
  ): PokemonRecord =
    PokemonRecord(
      _id = new ObjectId(),
      id = id,
      name = name,
      entry = entry,
      types = types,
      measurement = measurement,
      abilities = abilities,
      sprite = sprite,
      evolution = evolution,
      isMonoType = isMonoType.getOrElse(false),
      weaknesses = weaknesses
    )

  val codecRegistry: CodecRegistry =
    fromRegistries(
      fromProviders(
        classOf[PokemonRecord],
        classOf[PokemonType],
        classOf[Measurement],
        classOf[Ability],
        classOf[EvolutionChain],
        classOf[Evolution]
      ),
      DEFAULT_CODEC_REGISTRY
    )

}
