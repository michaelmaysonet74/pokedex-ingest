package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroTo, To}

final case class Measurement(
  height: Option[String],
  weight: Option[String]
)

object Measurement {

  implicit val decoder: To[Measurement] = macroTo[Measurement]

}
