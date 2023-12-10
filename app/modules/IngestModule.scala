package modules

import com.softwaremill.macwire.wire
import controllers.IngestController
import services.{IngestService, IngestServiceImpl}

trait IngestModule extends ControllerModule with PokedexModule {

  lazy val ingestService: IngestService = wire[IngestServiceImpl]
  lazy val ingestController: IngestController = wire[IngestController]

}
