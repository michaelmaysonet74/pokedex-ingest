package services

import models.{Evolution, PokemonRecord, UpdateEvolutionsResult}
import repositories.PokedexRepo

import scala.concurrent.{ExecutionContext, Future}

trait EvolutionService {

  def updateEvolutions(): Future[UpdateEvolutionsResult]

}

class EvolutionServiceImpl(
  pokedexRepo: PokedexRepo
)(implicit
  ec: ExecutionContext
) extends EvolutionService {

  private case class EvolutionChain(
    from: Option[Evolution],
    to: Evolution
  )

  override def updateEvolutions(): Future[UpdateEvolutionsResult] =
    pokedexRepo
      .getEvolvedPokemon()
      .flatMap { evolvedPokemon =>
        val evolutionsToByEvolutionsFrom =
          evolvedPokemon
            .map(convertEvolutionChain)
            .groupMap(_.from.flatMap(_.name))(_.to)
            .collect { case (Some(name), evolutionTo) => name -> evolutionTo }

        Future
          .traverse(evolutionsToByEvolutionsFrom.toSeq) { case (name, evolutionTo) =>
            pokedexRepo.updateEvolutionTo(name, evolutionTo)
          }
      }
      .map { results =>
        UpdateEvolutionsResult(
          success = results.foldLeft(true) { case (acc, result) => result && acc },
          updateSize = results.size
        )
      }

  private def convertEvolutionChain(
    pokemon: PokemonRecord
  ): EvolutionChain =
    EvolutionChain(
      from = pokemon.evolution.flatMap(_.from),
      to = Evolution(
        id = Some(pokemon.id.toString),
        name = pokemon.name
      )
    )

}
