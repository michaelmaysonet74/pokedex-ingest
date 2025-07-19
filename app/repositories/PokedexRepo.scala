package repositories

import clients.MongoDbClient
import models.{Evolution, PokemonRecord}
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.model.Filters.{equal, notEqual}
import org.mongodb.scala.model.Updates.set

import scala.concurrent.{ExecutionContext, Future}

trait PokedexRepo {

  def insert(
    pokemon: PokemonRecord
  ): Future[Boolean]

  def updateBaseStats(
    pokemon: PokemonRecord
  ): Future[Boolean]

  def updateCategory(
    pokemonRecord: PokemonRecord
  ): Future[Boolean]

  def updateEntry(
    pokemonRecord: PokemonRecord
  ): Future[Boolean]

  def updateEvolutionTo(
    evolutionFromName: String,
    evolutionTo: Seq[Evolution]
  ): Future[Boolean]

  def updateHeight(
    pokemonRecord: PokemonRecord
  ): Future[Boolean]

  def getEvolvedPokemon(): Future[Seq[PokemonRecord]]

  def getEvolvedPokemonByName(
    name: String
  ): Future[Seq[PokemonRecord]]

}

class PokedexRepoImpl(
  db: MongoDbClient
)(implicit
  ec: ExecutionContext
) extends PokedexRepo {

  override def insert(
    pokemon: PokemonRecord
  ): Future[Boolean] =
    db.pokemon
      .insertOne(pokemon)
      .toFuture()
      .map(_ => true)
      .recover { case _ => false }

  override def updateBaseStats(
    pokemon: PokemonRecord
  ): Future[Boolean] =
    pokemon.baseStats
      .map { baseStats =>
        update(
          filter = equal("id", pokemon.id),
          update = set("baseStats", baseStats)
        )
      }
      .getOrElse(Future.successful(false))

  override def updateCategory(
    pokemon: PokemonRecord
  ): Future[Boolean] =
    pokemon.category
      .map { category =>
        update(
          filter = equal("id", pokemon.id),
          update = set("category", category)
        )
      }
      .getOrElse(Future.successful(false))

  override def updateEntry(
    pokemon: PokemonRecord
  ): Future[Boolean] =
    pokemon.entry
      .map { entry =>
        update(
          filter = equal("id", pokemon.id),
          update = set("entry", entry)
        )
      }
      .getOrElse(Future.successful(false))

  override def updateEvolutionTo(
    evolutionFromName: String,
    evolutionTo: Seq[Evolution]
  ): Future[Boolean] =
    update(
      filter = equal("name", evolutionFromName),
      update = set("evolution.to", evolutionTo)
    )

  override def updateHeight(
    pokemonRecord: PokemonRecord
  ): Future[Boolean] =
    (
      for {
        measurement <- pokemonRecord.measurement
        height <- measurement.height
      } yield update(
        filter = equal("id", pokemonRecord.id),
        update = set("measurement.height", height)
      )
    ).getOrElse(Future.successful(false))

  override def getEvolvedPokemon(): Future[Seq[PokemonRecord]] =
    get(filter = notEqual("evolution.from", null))

  override def getEvolvedPokemonByName(
    name: String
  ): Future[Seq[PokemonRecord]] =
    get(filter = equal("evolution.from.name", name))

  private def get(
    filter: Bson
  ): Future[Seq[PokemonRecord]] =
    db.pokemon
      .find(filter)
      .toFuture()
      .recover { case _ => Seq.empty }

  private def update(
    filter: Bson,
    update: Bson
  ): Future[Boolean] =
    db.pokemon
      .updateOne(filter, update)
      .toFuture()
      .map(_ => true)
      .recover { case _ => false }

}
