package models

import caliban.client.SelectionBuilder
import clients.schemas.PokedexSchema

final case class EvolutionChain(
  from: Option[Evolution],
  to: Option[Seq[Evolution]] = None
)

object EvolutionChain {

  val fragment: SelectionBuilder[PokedexSchema.EvolutionChain, EvolutionChain] =
    PokedexSchema.EvolutionChain
      .from {
        Evolution.fragment
      }
      .map(evolutionFrom => EvolutionChain(evolutionFrom))

}
