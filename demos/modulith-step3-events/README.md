# Step 3 — Decoupling with Events

> Fix the violation using an **event-driven** approach.

## What This Demonstrates

The direct coupling is gone. `OrderService` now publishes an `OrderCompleted` event. The `InventoryListener` in the inventory module reacts to it — no cross-module imports needed.

## What Changed from Step 2

- Created `OrderCompleted` record (public event in the `order` module)
- Refactored `OrderService` to use `ApplicationEventPublisher` instead of `StockRepository`
- Created `InventoryListener` with `@ApplicationModuleListener` in `inventory.internal`
- `Stock` and `StockRepository` changed from `public` to package-private — they are now internal to the inventory module

> **Note:** `@ApplicationModuleListener` makes event handling **asynchronous and transactional** by default. Each event is processed in its own transaction.

## Key Files

| File | Purpose |
|------|---------|
| `OrderCompleted.java` | Public event record — the contract between modules |
| `OrderService.java` | Publishes `OrderCompleted` via `ApplicationEventPublisher` |
| `InventoryListener.java` | Reacts to the event, decrements stock |
| `ArchitectureTests.java` | Same test — now **passes** (GREEN) |

## How to Demo

1. **Show the code** — Walk through `OrderService` → `OrderCompleted` → `InventoryListener`.
2. **Run the test** — watch it turn **GREEN**.
3. **The point:** *"We haven't just fixed a bug; we've codified our architecture. This test is now a gatekeeper in our CI/CD pipeline."*

## Verification

```bash
mvn test
# → PASSES: Architecture verification is GREEN
```

```bash
mvn spring-boot:run
curl -X POST "http://localhost:8080/orders?productId=PROD-001"
# → Order created, stock decremented via event
```
