# Decisions

Every deliberate call made about plan, architecture, or scope for Nexus, kept here so "why did we do it this way" never has to be re-derived. Newest at the bottom of each section unless noted otherwise.

## Project identity

- **Name:** Nexus. Themed as a jewellery e-commerce platform (Tanishq-style) rather than a generic order system — chosen specifically because live gold/silver rate updates driving price recalculation is a genuinely good, non-generic Kafka use case (vs. a plain order queue).
- **Scope:** Standalone project, deliberately kept separate from CareerPilot AI — not integrated into it, own repo/directory.
- **Primary goal:** Learning Kafka in depth (interview-readiness first). Nexus is the hands-on scaffolding the Kafka curriculum runs on top of — the project is secondary to the learning, not the deliverable.

## Tech stack

- Java 21, Spring Boot 3.5.4, Spring Cloud 2025.0.0.
- Maven multi-module (parent `pom.xml`, packaging `pom`) — matches the pattern already used in this workspace's prior experiments (`apis-arch`, `microservice-exp`), so it's a consistent, familiar layout rather than a new convention.
- Eureka (Netflix, via `spring-cloud-starter-netflix-eureka-server`/`-client`) for service discovery — the standard choice in the Spring Cloud ecosystem.
- Spring Cloud Gateway (reactive) for the API gateway, with explicit per-service routes rather than the discovery-locator auto-routing (more production-realistic, keeps routing intent visible in config).
- Postgres for all persistent services.

## Architecture

- **Clean Architecture** per business service — `domain` / `application` / `infrastructure` / `presentation`, applied per-microservice (not per-module in a monolith).
- **Package layout choice:** both `port/in` and `port/out` interfaces live under `domain/port`, with concrete use-case implementations under `application/usecase`. This is a deliberate variant of the canonical Uncle-Bob-style split (which would put the interfaces in the Use Cases ring) — chosen because it's a common, working convention in Spring hexagonal-architecture codebases, not a deviation by accident. See `codeflow.md` for the full reasoning and the compile-time-vs-runtime distinction this creates.
- **Dependency rule:** `domain` depends on nothing (plain JDK only); `application` depends only on `domain`; `infrastructure` and `presentation` each depend only on `domain` — never on each other, never on `application`'s concrete classes. Verified empirically (no cross-layer imports found via grep) rather than just asserted.
- **Discovery-server and gateway are deliberately thin** — no Clean Architecture layering, since they carry no business logic. The layering is reserved for services that actually have business rules.
- **Naming convention:** use-case implementations are suffixed `Service` (e.g. `RegisterUserService`), not `...UseCaseImpl`. A style choice, not a structural rule — open to switching to `Impl` suffixes if preferred later.

## Database

- **One shared Postgres instance**, not one instance per service, with a database per service — `nexus_user`, `nexus_catalog`, `nexus_pricing`, `nexus_inventory`, `nexus_order`, `nexus_payment` — created via an init script. Chosen for local-dev simplicity; each service still only ever talks to its own database (no cross-service queries), so the logical boundary is preserved even though the physical instance is shared.
- `discovery-server`, `gateway`, and `notification-service` have no database — `notification-service` is intentionally stateless (just logs).

## User-service specifics

- Auth: register/login with BCrypt password hashing + JWT issuance (via jjwt), matching CareerPilot AI's JWT-based, stateless auth model.
- **`SecurityConfig` currently permits all requests.** The service issues JWTs but nothing validates them on incoming calls yet. This is a deliberate, flagged gap — not an oversight — left as a follow-up decision (enforce at user-service, or at the gateway) rather than bundled into the initial scaffold.

## Infrastructure

- Docker Compose and Kubernetes manifests deliberately mirror CareerPilot AI's existing conventions (multi-stage Dockerfile shape, Postgres StatefulSet in k8s, readiness/liveness via Spring Actuator) — consistency over reinventing a new infra style.
- Full Maven reactor verified to compile clean across all 9 modules before considering scaffolding "done."

## Workflow — how Claude and the user collaborate on this project

- **Kafka producer/consumer/topic/listener code is written by hand by the user**, copy-pasted from chat — never written to disk by Claude. This is the one deliberate exception to normal workflow, specifically because typing and running it is the actual mechanism by which the Kafka learning is meant to stick.
- **Everything else** (services, Postgres wiring, Docker, Kubernetes, this documentation) is written directly by Claude using normal tools — the user explicitly asked for the non-Kafka scaffolding to be built quickly so the Kafka lessons have real services to plug into.
- Kafka learning curriculum, phase/stage plan, and progress notes are tracked separately under `kafka/` at the project root (see `kafka/README.md`).

## Open / deferred decisions

- Whether to enforce JWT validation on incoming requests (user-service vs. gateway) — not yet decided.
- Whether to rename use-case implementations from `...Service` to `...UseCaseImpl` — offered, not decided.
- Whether Kafka listeners (once added) get filed under `infrastructure` (by technology) or a renamed `adapter/in` / `adapter/out` split (by role) — flagged as worth deciding deliberately once Kafka is actually being wired in, not defaulted.
