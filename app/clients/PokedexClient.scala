package clients

import caliban.client.Operations.RootQuery
import caliban.client.SelectionBuilder
import clients.schemas.PokedexSchema.{
  Ability => SchemaAbility,
  BaseStats => SchemaBaseStats,
  EvolutionChain => SchemaEvolutionChain,
  EvolutionFrom => SchemaEvolutionFrom,
  Measurement => SchemaMeasurement,
  Pokemon => SchemaPokemon,
  PokemonType => SchemaPokemonType,
  Query
}
import com.rallyhealth.weejson.v1.jackson.FromJson
import com.rallyhealth.weepickle.v1.WeePickle.ToScala
import models.{
  Ability,
  BaseStats,
  Evolution,
  EvolutionChain,
  Measurement,
  PokemonById,
  PokemonByIdResponse,
  PokemonType
}
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

  private val baseStatsFragment = SchemaPokemon.baseStats(
    SchemaBaseStats.hp ~
      SchemaBaseStats.attack ~
      SchemaBaseStats.defense ~
      SchemaBaseStats.specialAttack ~
      SchemaBaseStats.specialDefense ~
      SchemaBaseStats.speed
  )

  def getPokemonById(id: String): Future[Option[PokemonById]] = {
    val query: SelectionBuilder[RootQuery, Option[PokemonById]] =
      Query.pokemonById(id) {
        (
          SchemaPokemon.id ~
            SchemaPokemon.name ~
            SchemaPokemon.entry() ~
            SchemaPokemon.types ~
            SchemaPokemon.measurement(
              SchemaMeasurement.height ~
                SchemaMeasurement.weight
            ) ~
            SchemaPokemon.abilities(
              SchemaAbility.name ~
                SchemaAbility.effect() ~
                SchemaAbility.isHidden
            ) ~
            SchemaPokemon.sprite ~
            SchemaPokemon.evolution(
              SchemaEvolutionChain.from(
                SchemaEvolutionFrom.id ~
                  SchemaEvolutionFrom.name
              )
            ) ~
            SchemaPokemon.isMonoType ~
            SchemaPokemon.weaknesses ~
            baseStatsFragment
        )
          .map {
            case (
                  id,
                  name,
                  entry,
                  types,
                  measurement,
                  abilities,
                  sprite,
                  evolution,
                  isMonoType,
                  weaknesses,
                  baseStats
                ) =>
              PokemonById(
                id = id,
                name = name,
                entry = entry,
                types = types.map(_.map(_.map(convert))),
                measurement = measurement.map { case (height, weight) => Measurement(height, weight) },
                abilities = abilities.map(_.map(_.map { case (name, effect, isHidden) =>
                  Ability(name, effect, isHidden)
                })),
                sprite = sprite,
                evolution = evolution.flatMap(_.map { case (id, name) =>
                  EvolutionChain(from = Some(Evolution(id, name)))
                }),
                isMonoType = isMonoType,
                weaknesses = weaknesses.map(_.map(_.map(convert))),
                baseStats = baseStats.map(convert)
              )
          }
      }

    executeQuery(query)
  }

  def getPokemonBaseStatsById(id: String): Future[Option[PokemonById]] = {
    val query: SelectionBuilder[RootQuery, Option[PokemonById]] =
      Query.pokemonById(id) {
        (SchemaPokemon.id ~ baseStatsFragment)
          .map { case (id, baseStats) =>
            PokemonById(
              id = id,
              baseStats = baseStats.map(convert)
            )
          }
      }

    executeQuery(query)
  }

  private def executeQuery(
    query: SelectionBuilder[RootQuery, Option[PokemonById]]
  ): Future[Option[PokemonById]] = {
    val request = query.toRequest(uri"$pokedexUri")
    val data = Json.parse(request.body.show.replaceFirst("string: ", ""))

    ws.url(request.uri.toString()).post(data).map { response =>
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

  private def convert(pokemonType: SchemaPokemonType): PokemonType =
    pokemonType match {
      case SchemaPokemonType.Bug      => PokemonType.Bug
      case SchemaPokemonType.Dark     => PokemonType.Dark
      case SchemaPokemonType.Dragon   => PokemonType.Dragon
      case SchemaPokemonType.Electric => PokemonType.Electric
      case SchemaPokemonType.Fairy    => PokemonType.Fairy
      case SchemaPokemonType.Fighting => PokemonType.Fighting
      case SchemaPokemonType.Fire     => PokemonType.Fire
      case SchemaPokemonType.Flying   => PokemonType.Flying
      case SchemaPokemonType.Ghost    => PokemonType.Ghost
      case SchemaPokemonType.Grass    => PokemonType.Grass
      case SchemaPokemonType.Ground   => PokemonType.Ground
      case SchemaPokemonType.Ice      => PokemonType.Ice
      case SchemaPokemonType.Normal   => PokemonType.Normal
      case SchemaPokemonType.Poison   => PokemonType.Poison
      case SchemaPokemonType.Psychic  => PokemonType.Psychic
      case SchemaPokemonType.Rock     => PokemonType.Rock
      case SchemaPokemonType.Steel    => PokemonType.Steel
      case SchemaPokemonType.Water    => PokemonType.Water
    }

  private def convert(
    baseStats: (Int, Int, Int, Int, Int, Int)
  ): BaseStats = {
    val (hp, attack, defense, specialAttack, specialDefense, speed) = baseStats
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
