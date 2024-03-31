package models

import caliban.client.SelectionBuilder
import clients.schemas.PokedexSchema

final case class Ability(
  name: Option[String],
  effect: Option[String],
  isHidden: Option[Boolean]
)

object Ability {

  val fragment: SelectionBuilder[PokedexSchema.Ability, Ability] =
    (
      PokedexSchema.Ability.name ~
        PokedexSchema.Ability.effect() ~
        PokedexSchema.Ability.isHidden
    ).map { case (name, effect, isHidden) => Ability(name, effect, isHidden) }

}
