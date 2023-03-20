package clients

import clients.MongoDbClient.mongoClient
import com.mongodb.{ServerApi, ServerApiVersion}
import models.PokemonRecord
import org.mongodb.scala._

class MongoDbClient {

  def pokemon: MongoCollection[PokemonRecord] =
    mongoClient
      .getDatabase("pokedex")
      .withCodecRegistry(PokemonRecord.codecRegistry)
      .getCollection("pokemon")

}

object MongoDbClient {

  import scala.sys.env

  private val uri =
    env.getOrElse("MONGO_DB_URI", "mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000")

  private val mongoClientSettings = MongoClientSettings
    .builder()
    .applyConnectionString(ConnectionString(uri))
    .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
    .build()

  private val mongoClient: MongoClient = MongoClient(mongoClientSettings)

}
