package models

import caliban.client.SelectionBuilder
import clients.schemas.PokedexSchema

final case class PokemonTypeChartById(
  id: String,
  immunities: Option[List[Option[PokedexSchema.PokemonType]]],
  resistances: Option[List[Option[PokedexSchema.PokemonType]]],
  weaknesses: Option[List[Option[PokedexSchema.PokemonType]]]
)

object PokemonTypeChartById {

  def fragment: SelectionBuilder[PokedexSchema.Pokemon, PokemonTypeChartById] =
    (
      PokedexSchema.Pokemon.id ~
        PokedexSchema.Pokemon.immunities ~
        PokedexSchema.Pokemon.resistances ~
        PokedexSchema.Pokemon.weaknesses
    ).map { case (id, immunities, resistances, weaknesses) =>
      PokemonTypeChartById(id, immunities, resistances, weaknesses)
    }
}
