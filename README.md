# assert-log

Small Java/Maven example project that shows how to assert Logback logging output in
JUnit tests.

The project includes:

- `LogWorker`, which writes the same message at `TRACE`, `DEBUG`, `INFO`, `WARN`,
  and `ERROR` levels.
- `MemoryAppender`, a test helper based on Logback's `ListAppender` for capturing
  log events in memory.
- JUnit tests that assert logged message counts, log levels, and regex pattern
  matches.

## Requirements

- Java 21
- Maven 3.x

## Build and test

Run the test suite:

```bash
mvn test
```

Build the jar:

```bash
mvn package
```

The packaged application is created under `target/` as:

```text
target/assert-log-1.0.1.jar
```

## Run

After packaging, run the application with:

```bash
java -jar target/assert-log-1.0.1.jar
```

The application logs a startup message from `Application#main`.

## Project structure

```text
src/main/java/com/nexora/software/assertlog/
  Application.java   # application entry point
  LogWorker.java     # emits log messages at multiple levels

src/main/resources/
  logback.xml        # Logback configuration

src/test/java/com/nexora/software/assertlog/
  LogWorkerTest.java # logging assertions
  MemoryAppender.java # in-memory appender test helper
```

## Logging

Logback is configured in `src/main/resources/logback.xml`.

- Console logging is enabled through the root logger.
- `com.nexora.software` logs are written with a rolling file appender.
- Generated log files are written to the `log/` directory.
