package clients

import caliban.client.Operations.RootQuery
import caliban.client.SelectionBuilder
import clients.schemas.PokedexSchema._
import com.rallyhealth.weejson.v1.jackson.FromJson
import com.rallyhealth.weepickle.v1.WeePickle.ToScala
import models.{PokemonById, PokemonByIdResponse}
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import sttp.client3.UriContext

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success

class PokedexClient(
  ws: WSClient
)(implicit
  ec: ExecutionContext
) {

  import clients.PokedexClient._

  def getPokemonById(id: String): Future[Option[PokemonById]] = {

    val query: SelectionBuilder[RootQuery, Option[PokemonById]] =
      Query.pokemonById(id) {
        (
          Pokemon.id ~
            Pokemon.name ~
            Pokemon.entry() ~
            Pokemon.types ~
            Pokemon.measurement(
              Measurement.height ~
                Measurement.weight
            ) ~
            Pokemon.abilities(
              Ability.name ~
                Ability.effect() ~
                Ability.isHidden
            ) ~
            Pokemon.sprite ~
            Pokemon.evolution(
              EvolutionChain.from(
                EvolutionFrom.id ~
                  EvolutionFrom.name
              )
            ) ~
            Pokemon.isMonoType ~
            Pokemon.weaknesses
        ).map { case (id, name, entry, types, measurement, abilities, sprite, evolution, isMonoType, weaknesses) =>
          PokemonById(
            id,
            name,
            entry,
            types,
            measurement,
            abilities,
            sprite,
            evolution,
            isMonoType,
            weaknesses
          )
        }
      }

    val request = query.toRequest(uri"$pokedexUri")
    val data = Json.parse(request.body.show.replaceFirst("string: ", ""))

    ws.url(request.uri.toString()).post(data).map { response =>
      println(response.body)

      val tryPokemonById =
        FromJson(response.body).validate[PokemonByIdResponse](ToScala[PokemonByIdResponse])

      tryPokemonById match {
        case Success(pokemonByIdResponse) => Some(pokemonByIdResponse.data.pokemonById)
        case _                            => None
      }
    }
  }

}

object PokedexClient {

  import scala.sys.env

  private val pokedexUri =
    env.getOrElse("POKEDEX_URI", "http://localhost:4001/graphql")

}
