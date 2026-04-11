# Step 7 — Integration Testing with Scenario

> **`@ApplicationModuleTest`** and the **`Scenario`** API for fluent, event-driven integration tests.

## What This Demonstrates

Spring Modulith's testing support: `@ApplicationModuleTest` bootstraps only a single module's Spring context (with its dependencies stubbed), and the `Scenario` API lets you stimulate an action and verify that the expected events are published — all in a fluent, readable style.

## What Changed from Step 6

- `InventoryListener` restored to working state (no simulated failure)
- Added `OrderIntegrationTests` using `@ApplicationModuleTest` + `Scenario`

## Key Concepts

- **`@ApplicationModuleTest`** — Slice test that boots only the module under test. Other modules' beans are excluded or replaced with mocks.
- **`Scenario`** — Injected into test methods. Fluent API: `stimulate(…) → andWaitForEventOfType(…) → matching(…) → toArriveAndVerify(…)`.

## How to Run

```bash
mvn test
```

## How to Demo

1. **Show the test** — Open `OrderIntegrationTests.java` and walk through the `Scenario` API:
   ```java
   scenario.stimulate(() -> orderService.createOrder("PROD-001"))
       .andWaitForEventOfType(OrderCompleted.class)
       .matching(event -> event.productId().equals("PROD-001"))
       .toArriveAndVerify(event -> {
           assertThat(event.orderId()).isNotNull();
           assertThat(event.productId()).isEqualTo("PROD-001");
       });
   ```
2. **Run the tests** and show all green.
3. **The point:** *"You can test module interactions with real Spring context slices — no Mockito gymnastics, no full integration test overhead."*
