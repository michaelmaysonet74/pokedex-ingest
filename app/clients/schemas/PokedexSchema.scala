package clients.schemas

import caliban.client.CalibanClientError.DecodingError
import caliban.client.FieldBuilder._
import caliban.client._
import caliban.client.__Value._

object PokedexSchema {

  sealed trait GameVersion extends scala.Product with scala.Serializable { def value: String }
  object GameVersion {
    case object red extends GameVersion { val value: String = "red" }
    case object blue extends GameVersion { val value: String = "blue" }
    case object yellow extends GameVersion { val value: String = "yellow" }
    case object gold extends GameVersion { val value: String = "gold" }
    case object silver extends GameVersion { val value: String = "silver" }
    case object crystal extends GameVersion { val value: String = "crystal" }
    case object ruby extends GameVersion { val value: String = "ruby" }
    case object sapphire extends GameVersion { val value: String = "sapphire" }
    case object emerald extends GameVersion { val value: String = "emerald" }
    case object diamond extends GameVersion { val value: String = "diamond" }
    case object pearl extends GameVersion { val value: String = "pearl" }
    case object platinum extends GameVersion { val value: String = "platinum" }
    case object black extends GameVersion { val value: String = "black" }
    case object white extends GameVersion { val value: String = "white" }
    case object x extends GameVersion { val value: String = "x" }
    case object y extends GameVersion { val value: String = "y" }
    case object sun extends GameVersion { val value: String = "sun" }
    case object moon extends GameVersion { val value: String = "moon" }
    case object sword extends GameVersion { val value: String = "sword" }
    case object shield extends GameVersion { val value: String = "shield" }
    case object scarlet extends GameVersion { val value: String = "scarlet" }
    case object violet extends GameVersion { val value: String = "violet" }

    implicit val decoder: ScalarDecoder[GameVersion] = {
      case __StringValue("red")      => Right(GameVersion.red)
      case __StringValue("blue")     => Right(GameVersion.blue)
      case __StringValue("yellow")   => Right(GameVersion.yellow)
      case __StringValue("gold")     => Right(GameVersion.gold)
      case __StringValue("silver")   => Right(GameVersion.silver)
      case __StringValue("crystal")  => Right(GameVersion.crystal)
      case __StringValue("ruby")     => Right(GameVersion.ruby)
      case __StringValue("sapphire") => Right(GameVersion.sapphire)
      case __StringValue("emerald")  => Right(GameVersion.emerald)
      case __StringValue("diamond")  => Right(GameVersion.diamond)
      case __StringValue("pearl")    => Right(GameVersion.pearl)
      case __StringValue("platinum") => Right(GameVersion.platinum)
      case __StringValue("black")    => Right(GameVersion.black)
      case __StringValue("white")    => Right(GameVersion.white)
      case __StringValue("x")        => Right(GameVersion.x)
      case __StringValue("y")        => Right(GameVersion.y)
      case __StringValue("sun")      => Right(GameVersion.sun)
      case __StringValue("moon")     => Right(GameVersion.moon)
      case __StringValue("sword")    => Right(GameVersion.sword)
      case __StringValue("shield")   => Right(GameVersion.shield)
      case __StringValue("scarlet")  => Right(GameVersion.scarlet)
      case __StringValue("violet")   => Right(GameVersion.violet)
      case other                     => Left(DecodingError(s"Can't build GameVersion from input $other"))
    }
    implicit val encoder: ArgEncoder[GameVersion] = {
      case GameVersion.red      => __EnumValue("red")
      case GameVersion.blue     => __EnumValue("blue")
      case GameVersion.yellow   => __EnumValue("yellow")
      case GameVersion.gold     => __EnumValue("gold")
      case GameVersion.silver   => __EnumValue("silver")
      case GameVersion.crystal  => __EnumValue("crystal")
      case GameVersion.ruby     => __EnumValue("ruby")
      case GameVersion.sapphire => __EnumValue("sapphire")
      case GameVersion.emerald  => __EnumValue("emerald")
      case GameVersion.diamond  => __EnumValue("diamond")
      case GameVersion.pearl    => __EnumValue("pearl")
      case GameVersion.platinum => __EnumValue("platinum")
      case GameVersion.black    => __EnumValue("black")
      case GameVersion.white    => __EnumValue("white")
      case GameVersion.x        => __EnumValue("x")
      case GameVersion.y        => __EnumValue("y")
      case GameVersion.sun      => __EnumValue("sun")
      case GameVersion.moon     => __EnumValue("moon")
      case GameVersion.sword    => __EnumValue("sword")
      case GameVersion.shield   => __EnumValue("shield")
      case GameVersion.scarlet  => __EnumValue("scarlet")
      case GameVersion.violet   => __EnumValue("violet")
    }

    val values: scala.collection.immutable.Vector[GameVersion] = scala.collection.immutable.Vector(
      red,
      blue,
      yellow,
      gold,
      silver,
      crystal,
      ruby,
      sapphire,
      emerald,
      diamond,
      pearl,
      platinum,
      black,
      white,
      x,
      y,
      sun,
      moon,
      sword,
      shield,
      scarlet,
      violet
    )
  }

  sealed trait Language extends scala.Product with scala.Serializable { def value: String }
  object Language {
    case object en extends Language { val value: String = "en" }
    case object es extends Language { val value: String = "es" }

    implicit val decoder: ScalarDecoder[Language] = {
      case __StringValue("en") => Right(Language.en)
      case __StringValue("es") => Right(Language.es)
      case other               => Left(DecodingError(s"Can't build Language from input $other"))
    }
    implicit val encoder: ArgEncoder[Language] = {
      case Language.en => __EnumValue("en")
      case Language.es => __EnumValue("es")
    }

    val values: scala.collection.immutable.Vector[Language] = scala.collection.immutable.Vector(en, es)
  }

  sealed trait PokemonType extends scala.Product with scala.Serializable { def value: String }
  object PokemonType {
    case object Bug extends PokemonType { val value: String = "Bug" }
    case object Dark extends PokemonType { val value: String = "Dark" }
    case object Dragon extends PokemonType { val value: String = "Dragon" }
    case object Electric extends PokemonType { val value: String = "Electric" }
    case object Fairy extends PokemonType { val value: String = "Fairy" }
    case object Fighting extends PokemonType { val value: String = "Fighting" }
    case object Fire extends PokemonType { val value: String = "Fire" }
    case object Flying extends PokemonType { val value: String = "Flying" }
    case object Ghost extends PokemonType { val value: String = "Ghost" }
    case object Grass extends PokemonType { val value: String = "Grass" }
    case object Ground extends PokemonType { val value: String = "Ground" }
    case object Ice extends PokemonType { val value: String = "Ice" }
    case object Normal extends PokemonType { val value: String = "Normal" }
    case object Poison extends PokemonType { val value: String = "Poison" }
    case object Psychic extends PokemonType { val value: String = "Psychic" }
    case object Rock extends PokemonType { val value: String = "Rock" }
    case object Steel extends PokemonType { val value: String = "Steel" }
    case object Water extends PokemonType { val value: String = "Water" }

    implicit val decoder: ScalarDecoder[PokemonType] = {
      case __StringValue("Bug")      => Right(PokemonType.Bug)
      case __StringValue("Dark")     => Right(PokemonType.Dark)
      case __StringValue("Dragon")   => Right(PokemonType.Dragon)
      case __StringValue("Electric") => Right(PokemonType.Electric)
      case __StringValue("Fairy")    => Right(PokemonType.Fairy)
      case __StringValue("Fighting") => Right(PokemonType.Fighting)
      case __StringValue("Fire")     => Right(PokemonType.Fire)
      case __StringValue("Flying")   => Right(PokemonType.Flying)
      case __StringValue("Ghost")    => Right(PokemonType.Ghost)
      case __StringValue("Grass")    => Right(PokemonType.Grass)
      case __StringValue("Ground")   => Right(PokemonType.Ground)
      case __StringValue("Ice")      => Right(PokemonType.Ice)
      case __StringValue("Normal")   => Right(PokemonType.Normal)
      case __StringValue("Poison")   => Right(PokemonType.Poison)
      case __StringValue("Psychic")  => Right(PokemonType.Psychic)
      case __StringValue("Rock")     => Right(PokemonType.Rock)
      case __StringValue("Steel")    => Right(PokemonType.Steel)
      case __StringValue("Water")    => Right(PokemonType.Water)
      case other                     => Left(DecodingError(s"Can't build PokemonType from input $other"))
    }
    implicit val encoder: ArgEncoder[PokemonType] = {
      case PokemonType.Bug      => __EnumValue("Bug")
      case PokemonType.Dark     => __EnumValue("Dark")
      case PokemonType.Dragon   => __EnumValue("Dragon")
      case PokemonType.Electric => __EnumValue("Electric")
      case PokemonType.Fairy    => __EnumValue("Fairy")
      case PokemonType.Fighting => __EnumValue("Fighting")
      case PokemonType.Fire     => __EnumValue("Fire")
      case PokemonType.Flying   => __EnumValue("Flying")
      case PokemonType.Ghost    => __EnumValue("Ghost")
      case PokemonType.Grass    => __EnumValue("Grass")
      case PokemonType.Ground   => __EnumValue("Ground")
      case PokemonType.Ice      => __EnumValue("Ice")
      case PokemonType.Normal   => __EnumValue("Normal")
      case PokemonType.Poison   => __EnumValue("Poison")
      case PokemonType.Psychic  => __EnumValue("Psychic")
      case PokemonType.Rock     => __EnumValue("Rock")
      case PokemonType.Steel    => __EnumValue("Steel")
      case PokemonType.Water    => __EnumValue("Water")
    }

    val values: scala.collection.immutable.Vector[PokemonType] = scala.collection.immutable.Vector(
      Bug,
      Dark,
      Dragon,
      Electric,
      Fairy,
      Fighting,
      Fire,
      Flying,
      Ghost,
      Grass,
      Ground,
      Ice,
      Normal,
      Poison,
      Psychic,
      Rock,
      Steel,
      Water
    )
  }

  type Evolution
  object Evolution {

    /** Pokemon's id, can match pokedex number (not always).
      */
    def id: SelectionBuilder[Evolution, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("id", OptionOf(Scalar()))

    /** Pokemon's name
      */
    def name: SelectionBuilder[Evolution, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("name", OptionOf(Scalar()))
  }

  type Ability
  object Ability {
    def name: SelectionBuilder[Ability, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("name", OptionOf(Scalar()))
    def effect(language: scala.Option[Language] = None)(implicit
      encoder0: ArgEncoder[scala.Option[Language]]
    ): SelectionBuilder[Ability, scala.Option[String]] = _root_.caliban.client.SelectionBuilder
      .Field("effect", OptionOf(Scalar()), arguments = List(Argument("language", language, "Language")(encoder0)))
    def isHidden: SelectionBuilder[Ability, scala.Option[Boolean]] =
      _root_.caliban.client.SelectionBuilder.Field("isHidden", OptionOf(Scalar()))
  }

  type EvolutionFrom
  object EvolutionFrom {

    /** Pokemon's id, can match pokedex number (not always).
      */
    def id: SelectionBuilder[EvolutionFrom, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("id", OptionOf(Scalar()))

    /** Pokemon's name
      */
    def name: SelectionBuilder[EvolutionFrom, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("name", OptionOf(Scalar()))
  }

  type EvolutionChain
  object EvolutionChain {

    /** Represents a Pokemon which it evolves from in the chain.
      */
    def from[A](innerSelection: SelectionBuilder[EvolutionFrom, A]): SelectionBuilder[EvolutionChain, scala.Option[A]] =
      _root_.caliban.client.SelectionBuilder.Field("from", OptionOf(Obj(innerSelection)))
  }

  type Measurement
  object Measurement {

    /** Entity's height
      */
    def height: SelectionBuilder[Measurement, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("height", OptionOf(Scalar()))

    /** Entity's weight
      */
    def weight: SelectionBuilder[Measurement, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("weight", OptionOf(Scalar()))
  }

  type Pokemon
  object Pokemon {

    /** Pokemon's id, can match pokedex number (not always).
      */
    def id: SelectionBuilder[Pokemon, String] = _root_.caliban.client.SelectionBuilder.Field("id", Scalar())

    /** Pokemon's name
      */
    def name: SelectionBuilder[Pokemon, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("name", OptionOf(Scalar()))

    /** List of moves that this Pokemon can learn.
      */
    def moves: SelectionBuilder[Pokemon, scala.Option[List[scala.Option[String]]]] =
      _root_.caliban.client.SelectionBuilder.Field("moves", OptionOf(ListOf(OptionOf(Scalar()))))

    /** List of types that this Pokemon has, should not be more than two.
      */
    def types: SelectionBuilder[Pokemon, scala.Option[List[scala.Option[PokemonType]]]] =
      _root_.caliban.client.SelectionBuilder.Field("types", OptionOf(ListOf(OptionOf(Scalar()))))

    /** List of abilieties this Pokemon can have.
      */
    def abilities[A](
      innerSelection: SelectionBuilder[Ability, A]
    ): SelectionBuilder[Pokemon, scala.Option[List[scala.Option[A]]]] =
      _root_.caliban.client.SelectionBuilder.Field("abilities", OptionOf(ListOf(OptionOf(Obj(innerSelection)))))

    /** Pokemon's measurement
      */
    def measurement[A](innerSelection: SelectionBuilder[Measurement, A]): SelectionBuilder[Pokemon, scala.Option[A]] =
      _root_.caliban.client.SelectionBuilder.Field("measurement", OptionOf(Obj(innerSelection)))

    /** Determines if Pokemon only have one type or not.
      */
    def isMonoType: SelectionBuilder[Pokemon, scala.Option[Boolean]] =
      _root_.caliban.client.SelectionBuilder.Field("isMonoType", OptionOf(Scalar()))

    /** Pokedex flavor text entries for this Pokemon.
      */
    def entry(language: scala.Option[Language] = None, version: scala.Option[GameVersion] = None)(implicit
      encoder0: ArgEncoder[scala.Option[Language]],
      encoder1: ArgEncoder[scala.Option[GameVersion]]
    ): SelectionBuilder[Pokemon, scala.Option[String]] = _root_.caliban.client.SelectionBuilder.Field(
      "entry",
      OptionOf(Scalar()),
      arguments = List(
        Argument("language", language, "Language")(encoder0),
        Argument("version", version, "GameVersion")(encoder1)
      )
    )

    /** URL of Pokemon's sprite
      */
    def sprite: SelectionBuilder[Pokemon, scala.Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("sprite", OptionOf(Scalar()))

    /** Pokemon's evolution chain
      */
    def evolution[A](innerSelection: SelectionBuilder[EvolutionChain, A]): SelectionBuilder[Pokemon, scala.Option[A]] =
      _root_.caliban.client.SelectionBuilder.Field("evolution", OptionOf(Obj(innerSelection)))

    /** List of types that this Pokemon is weak against.
      */
    def weaknesses: SelectionBuilder[Pokemon, scala.Option[List[scala.Option[PokemonType]]]] =
      _root_.caliban.client.SelectionBuilder.Field("weaknesses", OptionOf(ListOf(OptionOf(Scalar()))))
  }

  type Query = _root_.caliban.client.Operations.RootQuery
  object Query {

    /** Returns the details of a pokemon by the provided id.
      */
    def pokemonById[A](id: String)(innerSelection: SelectionBuilder[Pokemon, A])(implicit
      encoder0: ArgEncoder[String]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, scala.Option[A]] =
      _root_.caliban.client.SelectionBuilder
        .Field("pokemonById", OptionOf(Obj(innerSelection)), arguments = List(Argument("id", id, "ID!")(encoder0)))

    /** Returns the details of a pokemon by the provided name.
      */
    def pokemonByName[A](name: String)(innerSelection: SelectionBuilder[Pokemon, A])(implicit
      encoder0: ArgEncoder[String]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, scala.Option[A]] =
      _root_.caliban.client.SelectionBuilder.Field(
        "pokemonByName",
        OptionOf(Obj(innerSelection)),
        arguments = List(Argument("name", name, "String!")(encoder0))
      )
  }

}
