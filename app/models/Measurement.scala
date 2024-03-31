package models

import caliban.client.SelectionBuilder
import clients.schemas.PokedexSchema

final case class Measurement(
  height: Option[String],
  weight: Option[String]
)

object Measurement {

  val fragment: SelectionBuilder[PokedexSchema.Measurement, Measurement] =
    (
      PokedexSchema.Measurement.height ~
        PokedexSchema.Measurement.weight
    ).map { case (height, weight) => Measurement(height, weight) }

}
