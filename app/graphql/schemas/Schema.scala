package graphql.schemas

import caliban.GraphQL

trait Schema {

  def api: GraphQL[Any]

}
