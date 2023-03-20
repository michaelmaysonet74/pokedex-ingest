package models

import clients.schemas.PokedexSchema.PokemonType
import com.rallyhealth.weepickle.v1.WeePickle.{macroTo, To}

final case class PokemonByIdResponse(
  data: PokemonByIdData
)

object PokemonByIdResponse {

  implicit val decoder: To[PokemonByIdResponse] = macroTo[PokemonByIdResponse]

}

final case class PokemonByIdData(
  pokemonById: PokemonById
)

object PokemonByIdData {

  implicit val decoder: To[PokemonByIdData] = macroTo[PokemonByIdData]

}

final case class PokemonById(
  id: String,
  name: Option[String],
  entry: Option[String],
  types: Option[List[Option[PokemonType]]],
  measurement: Option[Measurement],
  abilities: Option[List[Option[Ability]]],
  sprite: Option[String],
  evolution: Option[Evolution],
  isMonoType: Option[Boolean],
  weaknesses: Option[List[Option[PokemonType]]]
)

object PokemonById {

  def apply(
    id: String,
    name: Option[String],
    entry: Option[String],
    types: Option[List[Option[PokemonType]]],
    measurement: Option[(Option[String], Option[String])],
    abilities: Option[List[Option[(Option[String], Option[String], Option[Boolean])]]],
    sprite: Option[String],
    evolution: Option[Option[(Option[String], Option[String])]],
    isMonoType: Option[Boolean],
    weaknesses: Option[List[Option[PokemonType]]]
  ): PokemonById =
    PokemonById(
      id = id,
      name = name,
      entry = entry,
      types = types,
      measurement = measurement.map { case (height, weight) => Measurement(height, weight) },
      abilities = abilities.map(_.map(_.map { case (name, effect, isHidden) =>
        Ability(name, effect, isHidden)
      })),
      sprite = sprite,
      evolution = evolution.flatMap(_.map { case (id, name) => Evolution(id, name) }),
      isMonoType = isMonoType,
      weaknesses = weaknesses
    )

  implicit val decoder: To[PokemonById] = macroTo[PokemonById]

}
