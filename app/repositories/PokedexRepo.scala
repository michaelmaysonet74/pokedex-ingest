package repositories

import clients.MongoDbClient
import models.PokemonRecord
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._

import scala.concurrent.{ExecutionContext, Future}

class PokedexRepo(
  db: MongoDbClient
)(implicit
  ec: ExecutionContext
) extends Repository[PokemonRecord, Boolean] {

  override def insert(pokemon: PokemonRecord): Future[Boolean] =
    db.pokemon
      .insertOne(pokemon)
      .toFuture()
      .map(_ => true)
      .fallbackTo(Future.successful(false))

  def updateBaseStats(
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
          .fallbackTo(Future.successful(false))
      }
      .getOrElse(Future.successful(false))

}
