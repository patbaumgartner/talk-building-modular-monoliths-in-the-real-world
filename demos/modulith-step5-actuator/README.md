# Step 5 — The Runtime Inspector

> Expose the **live** module structure via Spring Boot Actuator.

## What This Demonstrates

The application module structure is available at runtime as a JSON endpoint. The system "knows" its own architecture — which modules exist, their dependencies, and which events connect them.

## What Changed from Step 4

- Added `spring-boot-starter-actuator` and `spring-modulith-actuator`
- Exposed the `modulith` and `health` actuator endpoints

## Key Files

| File | Purpose |
|------|---------|
| `application.properties` | `management.endpoints.web.exposure.include=modulith,health` |

## How to Run

```bash
mvn spring-boot:run
```

## How to Demo

1. **Hit the actuator endpoint:**
   ```bash
   curl http://localhost:8080/actuator/modulith | jq .
   ```
2. **Highlight** the `events` section — the system knows that `OrderCompleted` bridges the `order` and `inventory` modules.
3. **The point:** *"The application can describe its own architecture at runtime."*
