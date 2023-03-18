# play-framework-template-with-gql
Scala Play! Framework Template with GraphQL (Caliban)

## Getting Started

### Local Development

```bash
# Start Server
$ sbt run
# ...
# --- (Running the application, auto-reloading is enabled) ---
# [info] p.c.s.AkkaHttpServer - Listening for HTTP on /0:0:0:0:0:0:0:0:9000
# (Server started, use Enter to stop and go back to the console...)

```

<img width="1718" alt="Screenshot 2023-03-02 at 1 53 47 PM" src="https://user-images.githubusercontent.com/20152719/222537821-950e78bd-aa6f-4130-8bd5-dc309b840fdd.png">

### Re-generate Client Schema

```bash
$ sbt calibanGenClient  [Schema URL] app/graphql/schemas/clients/[Name]Schema.scala

# Example using the Countries GraphQL endpoint
$ sbt calibanGenClient https://countries.trevorblades.com/graphql app/graphql/schemas/clients/CountriesSchema.scala
```

<img width="1653" alt="Screenshot 2023-03-05 at 12 53 01 PM" src="https://user-images.githubusercontent.com/20152719/222980318-867541a6-8150-4a09-82f5-df4346a0e7cb.png">
