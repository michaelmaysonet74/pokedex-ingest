package modules

import clients.CountriesClient
import com.softwaremill.macwire.wire
import controllers.CountriesController
import services.{CountriesService, CountriesServiceImpl}

trait CountriesModule extends ControllerModule with ClientModule {

  lazy val countriesClient: CountriesClient = wire[CountriesClient]
  lazy val countriesService: CountriesService = wire[CountriesServiceImpl]
  lazy val countriesController: CountriesController = wire[CountriesController]

}
