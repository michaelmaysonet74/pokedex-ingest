package repositories

import clients.MongoDbClient
import models.{Evolution, PokemonRecord}
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.model.Filters.{and, equal, notEqual}
import org.mongodb.scala.model.Updates.set

import scala.concurrent.{ExecutionContext, Future}

trait PokedexRepo {

  def insert(
    pokemon: PokemonRecord
  ): Future[Boolean]

  def updateBaseStats(
    pokemon: PokemonRecord
  ): Future[Boolean]

  def updateEvolutionTo(
    evolutionFromName: String,
    evolutionTo: Seq[Evolution]
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
        db.pokemon
          .updateOne(
            filter = equal("id", pokemon.id),
            update = set("baseStats", baseStats)
          )
          .toFuture()
          .map(_ => true)
          .recover { case _ => false }
      }
      .getOrElse(Future.successful(false))

  override def updateEvolutionTo(
    evolutionFromName: String,
    evolutionTo: Seq[Evolution]
  ): Future[Boolean] =
    db.pokemon
      .updateOne(
        filter = equal("name", evolutionFromName),
        update = set("evolution.to", evolutionTo)
      )
      .toFuture()
      .map(_ => true)
      .recover { case _ => false }

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

}
