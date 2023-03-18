package modules

import play.api.libs.ws.WSClient

trait ClientModule {

  def wsClient: WSClient

}
