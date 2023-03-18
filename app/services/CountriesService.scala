package services

import clients.CountriesClient
import models.CountryResponse

import scala.concurrent.{ExecutionContext, Future}

trait CountriesService {

  def getCountryByCode(code: String): Future[Option[CountryResponse]]

}

class CountriesServiceImpl(
  countriesClient: CountriesClient
)(implicit
  ec: ExecutionContext
) extends CountriesService {

  override def getCountryByCode(code: String): Future[Option[CountryResponse]] =
    if (code.exists(_.isDigit))
      Future.successful(None)
    else {
      countriesClient.getCountryByCode(code.toUpperCase)
    }

}
