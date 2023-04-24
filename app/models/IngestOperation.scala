package models

import com.rallyhealth.weepickle.v1.WeePickle.{fromTo, FromTo}
import enumeratum._

sealed abstract class IngestOperation(val value: String) extends EnumEntry

object IngestOperation extends Enum[IngestOperation] {

  override def values: IndexedSeq[IngestOperation] = findValues

  case object InsertAll extends IngestOperation("InsertAll")
  case object UpdateBaseStats extends IngestOperation("UpdateBaseStats")

  implicit val decoder: FromTo[IngestOperation] = fromTo[String].bimap[IngestOperation](_.value, withNameInsensitive)

}
