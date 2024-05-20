package models

import caliban.client.SelectionBuilder
import clients.schemas.PokedexSchema

final case class PokemonTypes(
  primary: PokemonType,
  secondary: Option[PokemonType]
)

object PokemonTypes {

  val fragment: SelectionBuilder[PokedexSchema.PokemonTypes, PokemonTypes] = (
    PokedexSchema.PokemonTypes.primary ~
      PokedexSchema.PokemonTypes.secondary
  ).map { case (primary, secondary) =>
    PokemonTypes(
      primary = PokemonType.convert(primary),
      secondary = secondary.map(PokemonType.convert)
    )
  }

}
