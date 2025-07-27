package models

import com.rallyhealth.weepickle.v1.WeePickle.{fromTo, FromTo}
import enumeratum._

sealed abstract class IngestOperation(val value: String) extends EnumEntry

object IngestOperation extends Enum[IngestOperation] {

  override def values: IndexedSeq[IngestOperation] = findValues

  case object InsertAll extends IngestOperation("InsertAll")
  case object UpdateBaseStats extends IngestOperation("UpdateBaseStats")
  case object UpdateCategory extends IngestOperation("UpdateCategory")
  case object UpdateEntry extends IngestOperation("UpdateEntry")
  case object UpdateHeight extends IngestOperation("UpdateHeight")
  case object UpdateWeight extends IngestOperation("UpdateWeight")
  case object UpdateImmunities extends IngestOperation("UpdateImmunities")
  case object UpdateResistances extends IngestOperation("UpdateResistances")

  implicit val decoder: FromTo[IngestOperation] = fromTo[String].bimap[IngestOperation](_.value, withNameInsensitive)

}
