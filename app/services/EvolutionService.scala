package services

import models.Evolution
import repositories.PokedexRepo

import scala.concurrent.{ExecutionContext, Future}

trait EvolutionService {

  def updateEvolutions(): Future[Boolean]

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

  // TODO: Finish impl
  override def updateEvolutions(): Future[Boolean] =
    pokedexRepo.getEvolvedPokemon.map { evolvedPokemon =>
      val groupedByFromEvolutionsTo = evolvedPokemon
        .map { pokemon =>
          EvolutionChain(
            from = pokemon.evolution.flatMap(_.from),
            to = Evolution(
              id = Some(pokemon.id.toString),
              name = pokemon.name
            )
          )
        }
        .groupBy(_.from.flatMap(_.name))
        .map { case (key, evolutions) =>
          key -> evolutions.map(_.to)
        }
      println(groupedByFromEvolutionsTo.head)
      true
    }

}
