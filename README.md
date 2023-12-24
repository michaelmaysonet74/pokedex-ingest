# poke-dex-ingest
Pokedex to MongoDB Ingestion Job

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

### Generate Schema Types from SDL
```bash
$ sbt calibanGenClient [SDL schema path] app/clients/schema/PokedexSchema.scala
```
