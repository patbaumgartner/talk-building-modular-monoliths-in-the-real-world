# Step 6 — The Grand Finale

> Full **observability**, **tracing**, and the event publication **safety net**.

## What This Demonstrates

High-definition distributed tracing with Jaeger, plus Spring Modulith's Event Publication Registry acting as a safety net. When the `InventoryListener` fails, the event is persisted and can be retried — the Transactional Outbox pattern, built in.

## What Changed from Step 5

- Replaced individual modulith dependencies with `spring-modulith-starter-jdbc` (enables JDBC-based Event Publication Registry — the "safety net")
- Added `spring-boot-starter-opentelemetry` and `spring-boot-docker-compose`
- Added `spring-modulith-starter-insight` for module-level observability
- Added `datasource-micrometer-spring-boot` for JDBC tracing
- Created `compose.yaml` with Jaeger (OTLP on port 4318, UI on port 16686)
- `InventoryListener` now throws a `RuntimeException` to simulate failure

## Prerequisites

- **Docker** must be running (Boot 4's Docker Compose support auto-starts Jaeger)

## How to Run

```bash
mvn spring-boot:run
# Docker Compose will automatically start Jaeger
```

## How to Demo

1. **Trigger an order** (the listener will fail):
   ```bash
   curl -X POST "http://localhost:8080/orders?productId=PROD-001"
   ```
2. **Show the safety net** — Open the H2 console at `http://localhost:8080/h2-console`
   (JDBC URL: `jdbc:h2:mem:testdb`) and query:
   ```sql
   SELECT * FROM EVENT_PUBLICATION;
   ```
   The failed event appears with `STATUS` = `FAILED`.
3. **Show the trace** — Open Jaeger at `http://localhost:16686`. Find the trace showing spans for `OrderService` → event bus → `InventoryListener` with the error.
4. **The point:** *"We have the visibility of 50 microservices with the deployment simplicity of one single JAR."*
