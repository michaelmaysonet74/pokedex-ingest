package models


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

