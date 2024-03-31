package models

import caliban.client.SelectionBuilder
import clients.schemas.PokedexSchema

final case class PokemonBaseStatsById(
  id: String,
  baseStats: Option[BaseStats]
)

object PokemonBaseStatsById {

  val fragment: SelectionBuilder[PokedexSchema.Pokemon, PokemonBaseStatsById] =
    (
      PokedexSchema.Pokemon.id ~
        PokedexSchema.Pokemon.baseStats {
          BaseStats.fragment
        }
    ).map { case (id, baseStats) => PokemonBaseStatsById(id, baseStats) }

}
