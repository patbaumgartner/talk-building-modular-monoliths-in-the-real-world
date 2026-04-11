# Demo

> **Stack:** Spring Boot 4.0.5 · Spring Modulith 2.1.0-M4 · Java 21

---

## Demo Flow

This is the definitive demo script for 2026. By following this flow, you'll show how Spring Modulith turns a "simple monolith" into a sophisticated, architecturally-aware system.

### Step 1 — The "Architectural Debt" Run

**Objective:** Show that "it works on my machine" isn't enough for clean architecture.

1. **Run the app** — Start `modulith-step1-working-mess`.
2. **Trigger** — Execute a `POST` to `/orders`.
3. **The Reveal** — Show the code where `OrderService` is directly calling `StockRepository`.
4. **Wit:** *"In standard Spring, this is a success. In an architect's eyes, this is the start of a spaghetti monster."*

### Step 2 — The Red Flag (Verification)

**Objective:** Use the Modulith engine to catch the leak.

1. **Open the Test** — Navigate to `ArchitectureTests.java`.
2. **Run the Test** — Watch it turn **Red**.
3. **Analyze** — Show the console output. Modulith explicitly calls out the "forbidden" dependency from the `order` module into `inventory.internal`.

### Step 3 — The Decoupled Fix (Green Code)

**Objective:** Refactor to the "Allowed" flow from the diagram.

1. **Code Swap** — Show the transition to `OrderCompleted` (the public event).
2. **Run the Test Again** — Watch it turn **Green**.
3. **The Moment:** *"We haven't just fixed a bug; we've codified our architecture. This test is now a gatekeeper in our CI/CD pipeline."*

### Step 4 — The Living Blueprint (Documentation)

**Objective:** Visual proof of the new architecture.

1. **Generate** — Run the test that triggers `Documenter.writeDocumentation()`.
2. **Open the C4 Diagram** — View `components.puml` in the IDE. It's a default C4 component diagram.
3. **Open a Module Canvas** — View `module-order.adoc` or `module-inventory.adoc`. These summarise each module's base package, exposed events, and listened events.
4. **Open the Aggregating Document** — View `all-docs.adoc` which includes all diagrams and canvases.
5. **Comparison** — Compare the generated C4 diagram to the original architecture diagram. They should be nearly identical, proving the code *is* the documentation.

### Step 5 — The X-Ray (Actuator)

**Objective:** Show the system's "brain" at runtime.

1. **Endpoint** — Hit `localhost:8080/actuator/modulith`.
2. **Visualization** — Use a JSON formatter or the Modulith Explorer UI.
3. **Highlight** — Point out the `events` section. The system now "knows" that `OrderCompleted` is a bridge between modules.

### Step 6 — The Grand Finale (Observability & Safety)

**Objective:** Demonstrate high-definition tracing and the "Safety Net."

1. **Start Infrastructure** — Start the app (Boot 4's Docker Compose will spin up Jaeger automatically).
2. **The Failure** — Trigger an order. The `InventoryListener` throws an exception.
3. **The Safety Net** — Show the H2 console or the `EVENT_PUBLICATION` table. The event is still there, with `STATUS` = `FAILED`.
4. **The Trace** — Open Jaeger (`16686`). Show the trace starting in `OrderService`, hopping into the event bus, and ending in the `InventoryListener` span.
5. **Wit:** *"We have the visibility of 50 microservices with the deployment simplicity of one single JAR."*

### Step 7 — The Proof (Scenario Testing)

**Objective:** Show how to test module interactions with real Spring context slices.

1. **Open the Test** — Navigate to `OrderIntegrationTests.java`.
2. **Walk Through** — Explain `@ApplicationModuleTest` (bootstraps only the `order` module) and the `Scenario` API (`stimulate → andWaitForEventOfType → matching → toArriveAndVerify`).
3. **Run the Tests** — Watch all tests turn **Green**.
4. **Wit:** *"You can test module interactions with real Spring context slices — no Mockito gymnastics, no full integration test overhead."*

---

## Presenter Checklist

- [ ] **IDE** — Use the Structure View in IntelliJ 2026 to show module boundaries.
- [ ] **Docker** — Ensure Docker Desktop is running so `compose.yaml` works seamlessly in Steps 6 and 7.
- [ ] **Events** — Mention that Spring Modulith's Event Publication Registry handles the "Transactional Outbox" pattern automatically behind the scenes.

---

## Prerequisites

- **Java 21** — Ensure `JAVA_HOME` points to a JDK 21+ installation.
- **Maven 3.9+** — Available on `PATH`.
- **Docker Desktop** — Running (required for Steps 6 and 7, but verify early).
- **IDE** — IntelliJ IDEA 2026+ recommended for Modulith tooling support.

## Quick Reference

| Step | Project                          | Key Feature              | Test Result | Key Dependency Added                                        |
|------|----------------------------------|--------------------------|-------------|-------------------------------------------------------------|
| 1    | `modulith-step1-working-mess`    | Intentional coupling     | —           | `spring-boot-starter-webmvc`, `spring-boot-starter-data-jdbc` |
| 2    | `modulith-step2-verification`    | Architecture verification| RED         | `spring-modulith-starter-core`, `spring-modulith-starter-test` |
| 3    | `modulith-step3-events`          | Event-driven decoupling  | GREEN       | `spring-modulith-events-api`, `spring-modulith-runtime`     |
| 4    | `modulith-step4-documentation`   | Full auto-documentation  | GREEN       | `Documenter.writeDocumentation()` (via `spring-modulith-starter-test`)  |
| 5    | `modulith-step5-actuator`        | Runtime inspection       | GREEN       | `spring-boot-starter-actuator`, `spring-modulith-actuator`  |
| 6    | `modulith-step6-observability`   | Observability + safety   | GREEN       | `spring-modulith-starter-jdbc`, `spring-boot-starter-opentelemetry`, `spring-modulith-starter-insight` |
| 7    | `modulith-step7-scenario-testing`| Integration testing      | GREEN       | `@ApplicationModuleTest`, `Scenario` API                    |

## Package Structure

Spring Modulith detects modules as **direct sub-packages** of the main application class's package:

```
com.patbaumgartner.demo.modulith      ← Main application class
├── order                           ← order module (public API)
└── inventory                       ← inventory module (public API)
    └── internal                    ← inventory internals (encapsulated)
```

> See each project's `README.md` for detailed demo instructions and verification steps.
