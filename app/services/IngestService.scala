package services

import clients.PokedexClient
import models.{IngestOperation, IngestResult, PokemonBaseStatsById, PokemonById, PokemonRecord}
import repositories.PokedexRepo

import scala.concurrent.{ExecutionContext, Future}

trait IngestService {

  def ingest(
    start: Int,
    end: Int,
    operation: Option[IngestOperation]
  ): Future[IngestResult]

}

class IngestServiceImpl(
  pokedexClient: PokedexClient,
  pokedexRepo: PokedexRepo
)(implicit
  ec: ExecutionContext
) extends IngestService {

  import IngestServiceImpl._

  override def ingest(
    start: Int,
    end: Int,
    operation: Option[IngestOperation]
  ): Future[IngestResult] =
    operation match {
      case Some(IngestOperation.UpdateBaseStats) =>
        processBatchOperation(start, end, getPokemonBaseStatsById, pokedexRepo.updateBaseStats)

      case Some(IngestOperation.UpdateCategory) =>
        processBatchOperation(start, end, getPokemonById, pokedexRepo.updateCategory)

      case Some(IngestOperation.UpdateEntry) =>
        processBatchOperation(start, end, getPokemonById, pokedexRepo.updateEntry)

      case Some(IngestOperation.UpdateHeight) =>
        processBatchOperation(start, end, getPokemonById, pokedexRepo.updateHeight)

      case Some(IngestOperation.UpdateWeight) =>
        processBatchOperation(start, end, getPokemonById, pokedexRepo.updateWeight)

      case Some(IngestOperation.UpdateImmunities) =>
        processBatchOperation(start, end, getPokemonById, pokedexRepo.updateImmunities)

      case Some(IngestOperation.UpdateResistances) =>
        processBatchOperation(start, end, getPokemonById, pokedexRepo.updateResistances)

      case _ => processBatchOperation(start, end, getPokemonById, pokedexRepo.insert)
    }

  private def processBatchOperation[T](
    start: Int,
    end: Int,
    fetch: Int => Future[Option[T]],
    operation: PokemonRecord => Future[Boolean]
  ): Future[IngestResult] =
    getBatchPokemonById(start, end, fetch).flatMap { pokemon =>
      val pokemonRecords = pokemon.map {
        case pokemonById: PokemonById =>
          convertPokemonById(pokemonById)
        case pokemonBaseStatsById: PokemonBaseStatsById =>
          convertPokemonBaseStatsById(pokemonBaseStatsById)
      }
      
      Future
        .sequence(pokemonRecords.map(operation))
        .map { results =>
          results.foldLeft(true) { case (acc, c) => acc && c }
        }
        .map(IngestResult(_, count = pokemonRecords.size))
    }

  private def getBatchPokemonById[T](
    start: Int,
    end: Int,
    fetch: Int => Future[Option[T]]
  ): Future[List[T]] =
    Future.sequence((start to end).map(fetch).toList).map { batchPokemonById =>
      for {
        maybePokemonById <- batchPokemonById
        pokemonById <- maybePokemonById
      } yield pokemonById
    }

  private def getPokemonById(id: Int): Future[Option[PokemonById]] =
    pokedexClient.getPokemonById(id.toString)

  private def getPokemonBaseStatsById(id: Int): Future[Option[PokemonBaseStatsById]] =
    pokedexClient.getPokemonBaseStatsById(id.toString)

}

object IngestServiceImpl {

  private def convertPokemonById(pokemonById: PokemonById): PokemonRecord =
    PokemonRecord(
      id = pokemonById.id.toInt,
      name = pokemonById.name,
      entry = pokemonById.entry,
      category = pokemonById.category,
      types = pokemonById.types,
      measurement = pokemonById.measurement,
      abilities = pokemonById.abilities.map(_.collect { case Some(ability) => ability }),
      sprite = pokemonById.sprite,
      evolution = pokemonById.evolution,
      isMonoType = pokemonById.isMonoType,
      immunities = pokemonById.immunities.map(_.collect { case Some(immunity) => immunity.value }),
      resistances = pokemonById.resistances.map(_.collect { case Some(resistance) => resistance.value }),
      weaknesses = pokemonById.weaknesses.map(_.collect { case Some(weakness) => weakness.value }),
      baseStats = pokemonById.baseStats
    )

  private def convertPokemonBaseStatsById(pokemonById: PokemonBaseStatsById): PokemonRecord =
    PokemonRecord(
      id = pokemonById.id.toInt,
      baseStats = pokemonById.baseStats
    )

}
