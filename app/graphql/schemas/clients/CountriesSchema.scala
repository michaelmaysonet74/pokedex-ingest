package graphql.schemas.clients

import caliban.client.FieldBuilder._
import caliban.client._
import caliban.client.__Value._

object CountriesSchema {

  type _Any = String

  type Country
  object Country {
    def code: SelectionBuilder[Country, String] = _root_.caliban.client.SelectionBuilder.Field("code", Scalar())
    def name: SelectionBuilder[Country, String] = _root_.caliban.client.SelectionBuilder.Field("name", Scalar())
    def native: SelectionBuilder[Country, String] = _root_.caliban.client.SelectionBuilder.Field("native", Scalar())
    def phone: SelectionBuilder[Country, String] = _root_.caliban.client.SelectionBuilder.Field("phone", Scalar())
    def continent[A](innerSelection: SelectionBuilder[Continent, A]): SelectionBuilder[Country, A] =
      _root_.caliban.client.SelectionBuilder.Field("continent", Obj(innerSelection))
    def capital: SelectionBuilder[Country, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("capital", OptionOf(Scalar()))
    def currency: SelectionBuilder[Country, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("currency", OptionOf(Scalar()))
    def languages[A](innerSelection: SelectionBuilder[Language, A]): SelectionBuilder[Country, List[A]] =
      _root_.caliban.client.SelectionBuilder.Field("languages", ListOf(Obj(innerSelection)))
    def emoji: SelectionBuilder[Country, String] = _root_.caliban.client.SelectionBuilder.Field("emoji", Scalar())
    def emojiU: SelectionBuilder[Country, String] = _root_.caliban.client.SelectionBuilder.Field("emojiU", Scalar())
    def states[A](innerSelection: SelectionBuilder[State, A]): SelectionBuilder[Country, List[A]] =
      _root_.caliban.client.SelectionBuilder.Field("states", ListOf(Obj(innerSelection)))
  }

  type Continent
  object Continent {
    def code: SelectionBuilder[Continent, String] = _root_.caliban.client.SelectionBuilder.Field("code", Scalar())
    def name: SelectionBuilder[Continent, String] = _root_.caliban.client.SelectionBuilder.Field("name", Scalar())
    def countries[A](innerSelection: SelectionBuilder[Country, A]): SelectionBuilder[Continent, List[A]] =
      _root_.caliban.client.SelectionBuilder.Field("countries", ListOf(Obj(innerSelection)))
  }

  type Language
  object Language {
    def code: SelectionBuilder[Language, String] = _root_.caliban.client.SelectionBuilder.Field("code", Scalar())
    def name: SelectionBuilder[Language, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("name", OptionOf(Scalar()))
    def native: SelectionBuilder[Language, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("native", OptionOf(Scalar()))
    def rtl: SelectionBuilder[Language, Boolean] = _root_.caliban.client.SelectionBuilder.Field("rtl", Scalar())
  }

  type State
  object State {
    def code: SelectionBuilder[State, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("code", OptionOf(Scalar()))
    def name: SelectionBuilder[State, String] = _root_.caliban.client.SelectionBuilder.Field("name", Scalar())
    def country[A](innerSelection: SelectionBuilder[Country, A]): SelectionBuilder[State, A] =
      _root_.caliban.client.SelectionBuilder.Field("country", Obj(innerSelection))
  }

  type _Service
  object _Service {

    /** The sdl representing the federated service capabilities. Includes federation directives, removes federation
      * types, and includes rest of full schema after schema directives have been applied
      */
    def sdl: SelectionBuilder[_Service, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("sdl", OptionOf(Scalar()))
  }

  final case class StringQueryOperatorInput(
    eq: scala.Option[String] = None,
    ne: scala.Option[String] = None,
    in: scala.Option[List[scala.Option[String]]] = None,
    nin: scala.Option[List[scala.Option[String]]] = None,
    regex: scala.Option[String] = None,
    glob: scala.Option[String] = None
  )
  object StringQueryOperatorInput {
    implicit val encoder: ArgEncoder[StringQueryOperatorInput] = new ArgEncoder[StringQueryOperatorInput] {
      override def encode(value: StringQueryOperatorInput): __Value =
        __ObjectValue(
          List(
            "eq" -> value.eq.fold(__NullValue: __Value)(value => implicitly[ArgEncoder[String]].encode(value)),
            "ne" -> value.ne.fold(__NullValue: __Value)(value => implicitly[ArgEncoder[String]].encode(value)),
            "in" -> value.in.fold(__NullValue: __Value)(value =>
              __ListValue(
                value.map(value =>
                  value.fold(__NullValue: __Value)(value => implicitly[ArgEncoder[String]].encode(value))
                )
              )
            ),
            "nin" -> value.nin.fold(__NullValue: __Value)(value =>
              __ListValue(
                value.map(value =>
                  value.fold(__NullValue: __Value)(value => implicitly[ArgEncoder[String]].encode(value))
                )
              )
            ),
            "regex" -> value.regex.fold(__NullValue: __Value)(value => implicitly[ArgEncoder[String]].encode(value)),
            "glob" -> value.glob.fold(__NullValue: __Value)(value => implicitly[ArgEncoder[String]].encode(value))
          )
        )
    }
  }
  final case class CountryFilterInput(
    code: scala.Option[StringQueryOperatorInput] = None,
    currency: scala.Option[StringQueryOperatorInput] = None,
    continent: scala.Option[StringQueryOperatorInput] = None
  )
  object CountryFilterInput {
    implicit val encoder: ArgEncoder[CountryFilterInput] = new ArgEncoder[CountryFilterInput] {
      override def encode(value: CountryFilterInput): __Value =
        __ObjectValue(
          List(
            "code" -> value.code.fold(__NullValue: __Value)(value =>
              implicitly[ArgEncoder[StringQueryOperatorInput]].encode(value)
            ),
            "currency" -> value.currency.fold(__NullValue: __Value)(value =>
              implicitly[ArgEncoder[StringQueryOperatorInput]].encode(value)
            ),
            "continent" -> value.continent.fold(__NullValue: __Value)(value =>
              implicitly[ArgEncoder[StringQueryOperatorInput]].encode(value)
            )
          )
        )
    }
  }
  final case class ContinentFilterInput(code: scala.Option[StringQueryOperatorInput] = None)
  object ContinentFilterInput {
    implicit val encoder: ArgEncoder[ContinentFilterInput] = new ArgEncoder[ContinentFilterInput] {
      override def encode(value: ContinentFilterInput): __Value =
        __ObjectValue(
          List(
            "code" -> value.code.fold(__NullValue: __Value)(value =>
              implicitly[ArgEncoder[StringQueryOperatorInput]].encode(value)
            )
          )
        )
    }
  }
  final case class LanguageFilterInput(code: scala.Option[StringQueryOperatorInput] = None)
  object LanguageFilterInput {
    implicit val encoder: ArgEncoder[LanguageFilterInput] = new ArgEncoder[LanguageFilterInput] {
      override def encode(value: LanguageFilterInput): __Value =
        __ObjectValue(
          List(
            "code" -> value.code.fold(__NullValue: __Value)(value =>
              implicitly[ArgEncoder[StringQueryOperatorInput]].encode(value)
            )
          )
        )
    }
  }
  type Query = _root_.caliban.client.Operations.RootQuery
  object Query {
    def _entities[A](representations: List[_Any] = Nil)(
      onContinent: SelectionBuilder[Continent, A],
      onCountry: SelectionBuilder[Country, A],
      onLanguage: SelectionBuilder[Language, A]
    )(implicit
      encoder0: ArgEncoder[List[_Any]]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, List[scala.Option[A]]] =
      _root_.caliban.client.SelectionBuilder.Field(
        "_entities",
        ListOf(
          OptionOf(
            ChoiceOf(Map("Continent" -> Obj(onContinent), "Country" -> Obj(onCountry), "Language" -> Obj(onLanguage)))
          )
        ),
        arguments = List(Argument("representations", representations, "[_Any!]!")(encoder0))
      )
    def _service[A](
      innerSelection: SelectionBuilder[_Service, A]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, A] =
      _root_.caliban.client.SelectionBuilder.Field("_service", Obj(innerSelection))
    def countries[A](filter: scala.Option[CountryFilterInput] = None)(innerSelection: SelectionBuilder[Country, A])(
      implicit encoder0: ArgEncoder[scala.Option[CountryFilterInput]]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, List[A]] =
      _root_.caliban.client.SelectionBuilder.Field(
        "countries",
        ListOf(Obj(innerSelection)),
        arguments = List(Argument("filter", filter, "CountryFilterInput")(encoder0))
      )
    def country[A](code: String)(innerSelection: SelectionBuilder[Country, A])(implicit
      encoder0: ArgEncoder[String]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, scala.Option[A]] =
      _root_.caliban.client.SelectionBuilder
        .Field("country", OptionOf(Obj(innerSelection)), arguments = List(Argument("code", code, "ID!")(encoder0)))
    def continents[A](
      filter: scala.Option[ContinentFilterInput] = None
    )(innerSelection: SelectionBuilder[Continent, A])(implicit
      encoder0: ArgEncoder[scala.Option[ContinentFilterInput]]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, List[A]] =
      _root_.caliban.client.SelectionBuilder.Field(
        "continents",
        ListOf(Obj(innerSelection)),
        arguments = List(Argument("filter", filter, "ContinentFilterInput")(encoder0))
      )
    def continent[A](code: String)(innerSelection: SelectionBuilder[Continent, A])(implicit
      encoder0: ArgEncoder[String]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, scala.Option[A]] =
      _root_.caliban.client.SelectionBuilder
        .Field("continent", OptionOf(Obj(innerSelection)), arguments = List(Argument("code", code, "ID!")(encoder0)))
    def languages[A](filter: scala.Option[LanguageFilterInput] = None)(innerSelection: SelectionBuilder[Language, A])(
      implicit encoder0: ArgEncoder[scala.Option[LanguageFilterInput]]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, List[A]] =
      _root_.caliban.client.SelectionBuilder.Field(
        "languages",
        ListOf(Obj(innerSelection)),
        arguments = List(Argument("filter", filter, "LanguageFilterInput")(encoder0))
      )
    def language[A](code: String)(innerSelection: SelectionBuilder[Language, A])(implicit
      encoder0: ArgEncoder[String]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, scala.Option[A]] =
      _root_.caliban.client.SelectionBuilder
        .Field("language", OptionOf(Obj(innerSelection)), arguments = List(Argument("code", code, "ID!")(encoder0)))
  }

}
