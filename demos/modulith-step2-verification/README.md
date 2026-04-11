# Step 2 — The Whistleblower

> Add Spring Modulith to **catch** the architectural violation.

## What This Demonstrates

Same code as Step 1, but now Spring Modulith is on the classpath. A single test (`ArchitectureTests.verify()`) detects that `order` illegally depends on `inventory.internal`.

## What Changed from Step 1

- Added `spring-modulith-starter-core` and `spring-modulith-starter-test`
- Added `ArchitectureTests.java` with `ApplicationModules.of(...).verify()`
- Added Flyway with module-level migrations (`db/migration/order/`, `db/migration/inventory/`)

## Key Files

| File | Purpose |
|------|---------|
| `ArchitectureTests.java` | Runs `verify()` — expected to **fail** (RED) |
| `OrderService.java` | Still has the `StockRepository` violation |

## How to Demo

1. **Open** `ArchitectureTests.java` in the IDE.
2. **Run the test** — watch it turn **RED**.
3. **Show the console output** — Modulith explicitly calls out the forbidden dependency from `order` into `inventory.internal`.
4. **The point:** *"We now have an automated architecture gatekeeper."*

## Verification

```bash
mvn test
# → FAILS: "Module 'order' depends on non-exposed type ...inventory.internal.StockRepository"
```
