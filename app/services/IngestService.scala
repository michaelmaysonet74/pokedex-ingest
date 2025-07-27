package services

import clients.PokedexClient
import models.{IngestOperation, PokemonBaseStatsById, PokemonById, PokemonRecord}
import repositories.PokedexRepo

import scala.concurrent.{ExecutionContext, Future}

trait IngestService {

  def ingest(
    start: Int,
    end: Int,
    operation: Option[IngestOperation]
  ): Future[Boolean]

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
  ): Future[Boolean] =
    operation match {
      case Some(IngestOperation.UpdateBaseStats)   => updateBatchPokemonBaseStatsById(start, end)
      case Some(IngestOperation.UpdateCategory)    => updateBatchPokemonCategoryById(start, end)
      case Some(IngestOperation.UpdateEntry)       => updateBatchPokemonEntryById(start, end)
      case Some(IngestOperation.UpdateHeight)      => updateBatchPokemonHeightById(start, end)
      case Some(IngestOperation.UpdateWeight)      => updateBatchPokemonWeightById(start, end)
      case Some(IngestOperation.UpdateImmunities)  => updateBatchPokemonImmunitiesById(start, end)
      case Some(IngestOperation.UpdateResistances) => updateBatchPokemonResistancesById(start, end)
      case _                                       => insertBatchPokemonById(start, end)
    }

  private def insertBatchPokemonById(start: Int, end: Int): Future[Boolean] =
    getBatchPokemonById(start, end, getPokemonById).flatMap { pokemons =>
      Future
        .sequence(pokemons.map(convert).map(pokedexRepo.insert))
        .map { results =>
          results.foldLeft(true) { case (acc, c) => acc && c }
        }
    }

  private def updateBatchPokemonBaseStatsById(start: Int, end: Int): Future[Boolean] =
    getBatchPokemonById(start, end, getPokemonBaseStatsById).flatMap { pokemons =>
      Future
        .sequence(pokemons.map(convert).map(pokedexRepo.updateBaseStats))
        .map { results =>
          results.foldLeft(true) { case (acc, c) => acc && c }
        }
    }

  private def updateBatchPokemonCategoryById(start: Int, end: Int): Future[Boolean] =
    getBatchPokemonById(start, end, getPokemonById).flatMap { pokemons =>
      Future
        .sequence(pokemons.map(convert).map(pokedexRepo.updateCategory))
        .map { results =>
          results.foldLeft(true) { case (acc, c) => acc && c }
        }
    }

  private def updateBatchPokemonEntryById(start: Int, end: Int): Future[Boolean] =
    getBatchPokemonById(start, end, getPokemonById).flatMap { pokemons =>
      Future
        .sequence(pokemons.map(convert).map(pokedexRepo.updateEntry))
        .map { results =>
          results.foldLeft(true) { case (acc, c) => acc && c }
        }
    }

  private def updateBatchPokemonHeightById(start: Int, end: Int): Future[Boolean] =
    getBatchPokemonById(start, end, getPokemonById).flatMap { pokemons =>
      Future
        .sequence(pokemons.map(convert).map(pokedexRepo.updateHeight))
        .map { results =>
          results.foldLeft(true) { case (acc, c) => acc && c }
        }
    }

  private def updateBatchPokemonWeightById(start: Int, end: Int): Future[Boolean] =
    getBatchPokemonById(start, end, getPokemonById).flatMap { pokemons =>
      Future
        .sequence(pokemons.map(convert).map(pokedexRepo.updateWeight))
        .map { results =>
          results.foldLeft(true) { case (acc, c) => acc && c }
        }
    }

  private def updateBatchPokemonImmunitiesById(start: Int, end: Int): Future[Boolean] =
    getBatchPokemonById(start, end, getPokemonById).flatMap { pokemons =>
      Future
        .sequence(pokemons.map(convert).map(pokedexRepo.updateImmunities))
        .map { results =>
          results.foldLeft(true) { case (acc, c) => acc && c }
        }
    }

  private def updateBatchPokemonResistancesById(start: Int, end: Int): Future[Boolean] =
    getBatchPokemonById(start, end, getPokemonById).flatMap { pokemons =>
      Future
        .sequence(pokemons.map(convert).map(pokedexRepo.updateResistances))
        .map { results =>
          results.foldLeft(true) { case (acc, c) => acc && c }
        }
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

  private def convert(pokemonById: PokemonById): PokemonRecord =
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

  private def convert(pokemonById: PokemonBaseStatsById): PokemonRecord =
    PokemonRecord(
      id = pokemonById.id.toInt,
      baseStats = pokemonById.baseStats
    )

}
