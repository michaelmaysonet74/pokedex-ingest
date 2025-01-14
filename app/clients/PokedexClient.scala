package clients

import caliban.client.Operations.RootQuery
import caliban.client.SelectionBuilder
import clients.schemas.PokedexSchema.Query
import models.{PokemonBaseStatsById, PokemonById}

import scala.concurrent.{ExecutionContext, Future}

class PokedexClient(
  config: PokedexClientConfig
)(implicit
  val ec: ExecutionContext
) extends GraphQLClient {

  private implicit val uri: String = config.pokedexUri

  def getPokemonById(id: String): Future[Option[PokemonById]] = {
    val query: SelectionBuilder[RootQuery, Option[PokemonById]] =
      Query.pokemonById(id) {
        PokemonById.fragment
      }
    execute(query)
  }

  def getPokemonBaseStatsById(id: String): Future[Option[PokemonBaseStatsById]] = {
    val query: SelectionBuilder[RootQuery, Option[PokemonBaseStatsById]] =
      Query.pokemonById(id) {
        PokemonBaseStatsById.fragment
      }
    execute(query)
  }

}
