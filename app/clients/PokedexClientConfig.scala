package clients

import scala.sys.env

class PokedexClientConfig {

  val pokedexUri: String =
    env.getOrElse("POKEDEX_URI", "http://localhost:4000/graphql")

}
