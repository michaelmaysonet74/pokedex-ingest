package modules

import clients.{PokedexClient, PokedexClientConfig}
import com.softwaremill.macwire.wire
import repositories.{PokedexRepo, PokedexRepoImpl}

trait PokedexModule extends ClientModule {

  lazy val pokedexClient: PokedexClient = {
    val pokedexClientConfig: PokedexClientConfig = wire[PokedexClientConfig]
    wire[PokedexClient]
  }

  lazy val pokedexRepo: PokedexRepo = wire[PokedexRepoImpl]

}
