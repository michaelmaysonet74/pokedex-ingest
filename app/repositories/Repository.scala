package repositories

import scala.concurrent.Future

trait Repository[Input, Output] {

  def insert(i: Input): Future[Output]

}
