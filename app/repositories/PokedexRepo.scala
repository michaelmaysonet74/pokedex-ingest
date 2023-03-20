package repositories

import clients.MongoDbClient
import models.PokemonRecord

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

}
