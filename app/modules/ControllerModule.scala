package modules

import play.api.Logger
import play.api.mvc.ControllerComponents

import scala.concurrent.ExecutionContext

trait ControllerModule {

  implicit def ec: ExecutionContext

  implicit def logger: Logger

  def controllerComponents: ControllerComponents

}
