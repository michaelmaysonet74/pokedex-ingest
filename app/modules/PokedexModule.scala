package modules

import clients.PokedexClient
import com.softwaremill.macwire.wire
import repositories.{PokedexRepo, PokedexRepoImpl}

trait PokedexModule extends ClientModule {

  lazy val pokedexClient: PokedexClient = wire[PokedexClient]
  lazy val pokedexRepo: PokedexRepo = wire[PokedexRepoImpl]

}
