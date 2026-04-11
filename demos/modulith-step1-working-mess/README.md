# Step 1 — The "Working Mess"

> Plain Spring Boot app with **intentional** architectural coupling.

## What This Demonstrates

`OrderService` directly injects `StockRepository` from `inventory.internal` — crossing module boundaries. Standard Spring doesn't care, but this is the start of a spaghetti monolith.

## Key Files

| File | Purpose |
|------|---------|
| `OrderService.java` | Directly injects `StockRepository` — the violation |
| `OrderController.java` | `POST /orders?productId=PROD-001` |
| `inventory/internal/StockRepository.java` | Internal repository that should not be accessed from outside |

## How to Run

```bash
mvn spring-boot:run
```

## How to Demo

1. **Start the app** and create an order:
   ```bash
   curl -X POST "http://localhost:8080/orders?productId=PROD-001"
   ```
2. **Show the code** — Open `OrderService.java` and point out the direct `StockRepository` injection.
3. **The point:** *"It works. But in an architect's eyes, this is the start of a spaghetti monster."*

## Stack

- Spring Boot 4.0.5
- Spring Data JDBC + H2
- No Spring Modulith (yet)
- No tests — intentional; verification starts in Step 2
