package modules

import controllers.EvolutionController
import com.softwaremill.macwire.wire
import services.{EvolutionService, EvolutionServiceImpl}

trait EvolutionModule extends ControllerModule with PokedexModule {

  lazy val evolutionService: EvolutionService = wire[EvolutionServiceImpl]
  lazy val evolutionController: EvolutionController = wire[EvolutionController]

}
