package models

import caliban.client.SelectionBuilder
import clients.schemas.PokedexSchema

final case class Evolution(
  id: Option[String],
  name: Option[String]
)

object Evolution {

  val fragment: SelectionBuilder[PokedexSchema.EvolutionFrom, Evolution] =
    (
      PokedexSchema.EvolutionFrom.id ~
        PokedexSchema.EvolutionFrom.name
    ).map { case (id, name) => Evolution(id, name) }

}
