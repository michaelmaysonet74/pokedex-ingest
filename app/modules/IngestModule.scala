package modules

import clients.PokedexClient
import com.softwaremill.macwire.wire
import controllers.IngestController
import repositories.PokedexRepo
import services.{IngestService, IngestServiceImpl}

trait IngestModule extends ControllerModule with ClientModule {

  lazy val ingestService: IngestService = {
    lazy val pokedexClient: PokedexClient = wire[PokedexClient]
    lazy val pokedexRepo: PokedexRepo = wire[PokedexRepo]
    wire[IngestServiceImpl]
  }

  lazy val ingestController: IngestController = wire[IngestController]

}
