package models

import clients.schemas.PokedexSchema.PokemonType
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.bson.ObjectId

final case class PokemonRecord(
  _id: ObjectId,
  id: String,
  name: Option[String],
  entry: Option[String],
  types: Option[List[Option[PokemonType]]],
  measurement: Option[Measurement],
  abilities: Option[List[Option[Ability]]],
  sprite: Option[String],
  isMonoType: Boolean,
  weaknesses: Option[List[Option[PokemonType]]]
)

object PokemonRecord {

  def apply(
    id: String,
    name: Option[String],
    entry: Option[String],
    types: Option[List[Option[PokemonType]]],
    measurement: Option[Measurement],
    abilities: Option[List[Option[Ability]]],
    sprite: Option[String],
    isMonoType: Option[Boolean],
    weaknesses: Option[List[Option[PokemonType]]]
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
      isMonoType = isMonoType.getOrElse(false),
      weaknesses = weaknesses
    )

  val codecRegistry: CodecRegistry =
    fromRegistries(
      fromProviders(classOf[PokemonRecord], classOf[PokemonType], classOf[Measurement], classOf[Ability]),
      DEFAULT_CODEC_REGISTRY
    )

}
