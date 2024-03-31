package models

import caliban.client.SelectionBuilder
import clients.schemas.PokedexSchema

final case class BaseStats(
  hp: Int,
  attack: Int,
  defense: Int,
  specialAttack: Int,
  specialDefense: Int,
  speed: Int
)

object BaseStats {

  val fragment: SelectionBuilder[PokedexSchema.BaseStats, BaseStats] =
    (
      PokedexSchema.BaseStats.hp ~
        PokedexSchema.BaseStats.attack ~
        PokedexSchema.BaseStats.defense ~
        PokedexSchema.BaseStats.specialAttack ~
        PokedexSchema.BaseStats.specialDefense ~
        PokedexSchema.BaseStats.speed
    ).map {
      case (
            hp,
            attack,
            defense,
            specialAttack,
            specialDefense,
            speed
          ) =>
        BaseStats(
          hp,
          attack,
          defense,
          specialAttack,
          specialDefense,
          speed
        )
    }

}
