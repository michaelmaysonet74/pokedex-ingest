package services

import clients.PokedexClient
import models.{PokemonById, PokemonRecord}
import repositories.PokedexRepo

import scala.concurrent.{ExecutionContext, Future}

trait IngestService {
  def ingest(start: Int, end: Int): Future[Boolean]

}

class IngestServiceImpl(
  pokedexClient: PokedexClient,
  pokedexRepo: PokedexRepo
)(implicit
  ec: ExecutionContext
) extends IngestService {

  override def ingest(start: Int, end: Int): Future[Boolean] =
    getBatchPokemonById(start, end).flatMap { pokemons =>
      Future
        .sequence(pokemons.map(convert).map(pokedexRepo.insert))
        .map { results =>
          results.foldLeft(true) { case (acc, c) => acc && c }
        }
    }

  private def getBatchPokemonById(start: Int, end: Int): Future[List[PokemonById]] =
    Future.sequence((start to end).map(getPokemonById).toList).map { batchPokemonById =>
      for {
        maybePokemonById <- batchPokemonById
        pokemonById <- maybePokemonById
      } yield pokemonById
    }

  private def getPokemonById(id: Int): Future[Option[PokemonById]] =
    pokedexClient.getPokemonById(id.toString)

  private def convert(pokemonById: PokemonById): PokemonRecord =
    PokemonRecord(
      id = pokemonById.id.toInt,
      name = pokemonById.name,
      entry = pokemonById.entry,
      types = pokemonById.types.map(_.collect { case Some(pokemonType) => pokemonType.value }),
      measurement = pokemonById.measurement,
      abilities = pokemonById.abilities.map(_.collect { case Some(ability) => ability }),
      sprite = pokemonById.sprite,
      evolution = pokemonById.evolution,
      isMonoType = pokemonById.isMonoType,
      weaknesses = pokemonById.weaknesses.map(_.collect { case Some(weakness) => weakness.value })
    )

}
