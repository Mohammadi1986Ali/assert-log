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
