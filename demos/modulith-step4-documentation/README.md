# Step 4 — The Living Blueprint

> **Auto-generate** architecture documentation from the actual code.

## What This Demonstrates

Spring Modulith's `Documenter.writeDocumentation()` produces the full documentation suite — C4 component diagrams (PlantUML), module canvases (Asciidoctor), an aggregating document, and module metadata — directly from the module structure. The code *is* the documentation — no manual diagrams that drift out of sync.

## What Changed from Step 3

- Extended `ArchitectureTests` to also generate full documentation using `Documenter.writeDocumentation()` — C4 diagrams, module canvases, aggregating document, and module metadata (available via `spring-modulith-starter-test`, added in Step 2)

## Key Files

| File | Purpose |
|------|---------|
| `ArchitectureTests.java` | Combined `verifyAndDocument()` test — verifies architecture and writes full documentation via `writeDocumentation()` |

## How to Demo

1. **Run the tests:**
   ```bash
   mvn test
   ```
2. **Open the C4 diagram** — `target/spring-modulith-docs/components.puml` in the IDE.
3. **Open a module canvas** — `target/spring-modulith-docs/module-inventory.adoc` to see the module's base package, listened events, etc.
4. **Open the aggregating document** — `target/spring-modulith-docs/all-docs.adoc` includes everything.
5. **Compare** the generated C4 diagram to the original architecture diagram. They should match.
6. **The point:** *"The code IS the documentation. It can never drift."*

## Verification

```bash
mvn test
ls target/spring-modulith-docs/
# → components.puml, module-*.puml (C4 diagrams), module-*.adoc (canvases), and all-docs.adoc are generated
```
