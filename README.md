# quarkus-config-mapping-repro

Reproducer for a Quarkus 4.0.0.Beta1 regression: a `@ConfigMapping` that lives in a
dependency jar (indexed through `META-INF/jandex.idx`) is ignored when the application also
uses an extension whose own config roots end up earlier in the index, such as `quarkus-cache`.

Upstream issue: https://github.com/quarkusio/quarkus/issues/ISSUE_NUMBER

## Layout

- `lib`: plain jar with a Jandex index, containing `ReproConfig` (`@ConfigMapping(prefix = "repro")`)
  and an `@ApplicationScoped` bean that injects it.
- `app`: depends on `lib`, `quarkus-arc` and `quarkus-cache`, sets `repro.name=x`, and has a
  `@QuarkusTest` that injects `ReproConfig`.

## Running it

Requires Java 21+ and Maven.

| Command | Result |
|---|---|
| `mvn clean verify` | Fails: `Unsatisfied dependency for type org.acme.lib.ReproConfig` |
| `mvn clean verify -Dno-cache` | Passes (`quarkus-cache` removed) |
| `mvn clean verify -Dquarkus.version=3.40.1 -Dquarkus.junit.artifact=quarkus-junit5` | Passes |

The cause is `break outer` instead of `continue outer` in
`ConfigGenerationBuildStep#discoverConfigMappings`; see the issue for details.
