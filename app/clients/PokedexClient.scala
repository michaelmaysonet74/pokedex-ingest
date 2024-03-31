package clients

import caliban.client.Operations.RootQuery
import caliban.client.SelectionBuilder
import clients.schemas.PokedexSchema.{Pokemon => SchemaPokemon, PokemonType => SchemaPokemonType, Query}
import models.{Ability, BaseStats, EvolutionChain, Measurement, PokemonBaseStatsById, PokemonById, PokemonType}

import scala.concurrent.{ExecutionContext, Future}

class PokedexClient(
)(implicit
  val ec: ExecutionContext
) extends GraphQLClient {

  import clients.PokedexClient._

  def getPokemonById(id: String): Future[Option[PokemonById]] = {
    val query: SelectionBuilder[RootQuery, Option[PokemonById]] =
      Query.pokemonById(id) {
        (
          SchemaPokemon.id ~
            SchemaPokemon.name ~
            SchemaPokemon.entry() ~
            SchemaPokemon.category() ~
            SchemaPokemon.types ~
            SchemaPokemon.measurement {
              Measurement.fragment
            } ~
            SchemaPokemon.abilities {
              Ability.fragment
            } ~
            SchemaPokemon.sprite ~
            SchemaPokemon.evolution {
              EvolutionChain.fragment
            } ~
            SchemaPokemon.isMonoType ~
            SchemaPokemon.weaknesses ~
            SchemaPokemon.baseStats {
              BaseStats.fragment
            }
        ).map {
          case (
                id,
                name,
                entry,
                category,
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
              category = category,
              types = types.map(_.map(_.map(convert))),
              measurement = measurement,
              abilities = abilities,
              sprite = sprite,
              evolution = evolution,
              isMonoType = isMonoType,
              weaknesses = weaknesses.map(_.map(_.map(convert))),
              baseStats = baseStats
            )
        }
      }
    execute(query)
  }

  def getPokemonBaseStatsById(id: String): Future[Option[PokemonBaseStatsById]] = {
    val query: SelectionBuilder[RootQuery, Option[PokemonBaseStatsById]] =
      Query.pokemonById(id) {
        (
          SchemaPokemon.id ~
            SchemaPokemon.baseStats {
              BaseStats.fragment
            }
        ).mapN(PokemonBaseStatsById)
      }
    execute(query)
  }

}

object PokedexClient {

  private implicit val pokedexUri: String = {
    import scala.sys.env
    env.getOrElse("POKEDEX_URI", "http://localhost:4000/graphql")
  }

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

}
