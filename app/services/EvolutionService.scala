package services

import models.{Evolution, PokemonRecord, UpdateEvolutionsResult}
import repositories.PokedexRepo

import scala.concurrent.{ExecutionContext, Future}

trait EvolutionService {

  def updateEvolutions(
    maybePokemonName: Option[String]
  ): Future[UpdateEvolutionsResult]

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

  override def updateEvolutions(
    maybePokemonName: Option[String]
  ): Future[UpdateEvolutionsResult] =
    for {
      evolvedPokemon <- maybePokemonName match {
        case None       => pokedexRepo.getEvolvedPokemon()
        case Some(name) => pokedexRepo.getEvolvedPokemonByName(name)
      }

      evolutionsToByEvolutionsFrom =
        evolvedPokemon
          .map(convertEvolutionChain)
          .groupMap(_.from.flatMap(_.name))(_.to)
          .collect { case (Some(name), evolutionTo) => name -> evolutionTo }

      results <- Future.traverse(evolutionsToByEvolutionsFrom.toSeq) { case (name, evolutionTo) =>
        pokedexRepo.updateEvolutionTo(name, evolutionTo)
      }
    } yield {
      val updateSize = results.iterator.size
      val success = if (updateSize == 0) false else results.iterator.forall(identity)
      UpdateEvolutionsResult(success, updateSize)
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
