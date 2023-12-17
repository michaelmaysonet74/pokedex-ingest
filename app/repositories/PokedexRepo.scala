package repositories

import clients.MongoDbClient
import models.{Evolution, PokemonRecord}
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

  def getEvolvedPokemon(): Future[Seq[PokemonRecord]]

  def updateEvolutionTo(
    evolutionFromName: String,
    evolutionTo: Seq[Evolution]
  ): Future[Boolean]

}

class PokedexRepoImpl(
  db: MongoDbClient
)(implicit
  ec: ExecutionContextq
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

  override def getEvolvedPokemon(): Future[Seq[PokemonRecord]] =
    db.pokemon
      .find(filter = notEqual("evolution.from", null))
      .toFuture()
      .recover { case _ => Seq.empty }

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

}
