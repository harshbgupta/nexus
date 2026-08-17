# Changelog

A running log of code changes, plan changes, and decisions, newest entry at the top. This is the "what happened and when," complementary to `decision.md` (the "what we decided, and why, as it currently stands") and `codeflow.md` (the "how the code works").

---

## 2026-08-16 — Documentation set created

- Added `documentation/code/decision.md`, `codeflow.md`, `changelog.md` (this file) to capture project decisions, code flow, and history going forward.

## 2026-08-16 — Kafka curriculum & docs scaffolded

- Full 5-phase, 35-stage Kafka curriculum finalized (novice → expert, interview-prep checkpoints threaded through).
- Created `kafka/` folder at project root: `README.md` (index + full plan + stage checklist), `phase-0-foundations.md` through `phase-5-interview-prep.md` (one file per phase, each stage with a **Plan** line and a **Notes** placeholder to fill in as completed).
- **Phase 0 completed** (theory only, no code): why Kafka's log-based model exists, the three delivery-semantics guarantees, and core vocabulary (topic, partition, offset, broker, consumer group, replication factor, leader/follower/ISR, segments). Self-check questions answered.
- Deep-dive Q&A also covered ahead of schedule (not tied to a specific stage): downstream vs. upstream services, replication factor mechanics (with a worked 3-broker/RF=3 diagram), log segments, ZooKeeper's former role and why KRaft replaces it, what happens on broker death vs. leader-partition death, and how partition-leader election differs from KRaft-controller election.
- **Decision confirmed:** Kafka producer/consumer/topic code will be written by hand by the user (copy-pasted from chat), never written to disk directly — the one deliberate exception to normal workflow on this project.

## 2026-08-16 — Nexus scaffolding built and verified

- Project named **Nexus** — jewellery e-commerce theme (Tanishq-style), standalone from CareerPilot AI.
- Parent Maven POM created at `nexus/pom.xml` (packaging `pom`, Java 21, Spring Boot 3.5.4, Spring Cloud 2025.0.0), matching the multi-module pattern from this workspace's prior `apis-arch`/`microservice-exp` experiments.
- Built all 9 modules:
  - `discovery-server` — Eureka Server, thin, no business logic.
  - `gateway` — Spring Cloud Gateway, explicit routes to each service.
  - `user-service` — full Clean Architecture (domain/application/infrastructure/presentation), Postgres (`nexus_user`), register/login/JWT (BCrypt + jjwt). `SecurityConfig` currently permits all requests (flagged, not yet enforced).
  - `catalog-service` — jewellery items (sku, metal, purity, weight, making charge), Postgres (`nexus_catalog`).
  - `pricing-service` — gold/silver rate records, Postgres (`nexus_pricing`).
  - `inventory-service` — stock per sku/branch, Postgres (`nexus_inventory`).
  - `order-service` — place/get/list orders, Postgres (`nexus_order`).
  - `payment-service` — simulated payment (randomized success/fail), Postgres (`nexus_payment`).
  - `notification-service` — stateless, logs only, no database.
  - All 6 business services follow the identical Clean Architecture shape: one aggregate, one repository port + JPA adapter, one controller with create/getById/list.
- `infra/docker/docker-compose.yml` — one shared Postgres container (init script creates all 6 `nexus_*` databases), one entry per service, multi-stage Dockerfiles per service — mirrors CareerPilot AI's compose conventions.
- `infra/k8s/` — namespace, Postgres StatefulSet, one Deployment + Service per app service, readiness/liveness via Actuator — mirrors CareerPilot AI's k8s conventions.
- Full Maven reactor verified to compile clean (`mvn compile` from `nexus/`, all 9 modules SUCCESS).
- `.gitignore` added at project root.

## 2026-08-16 — Architecture literacy pass

- Extensive Q&A walkthrough of `user-service`'s code: every class's purpose, the full `login` request trace, the dependency-inversion rule, and reconciling several different ways of drawing the domain/application/infrastructure/presentation dependency graph (compile-time imports vs. conceptual/runtime call flow) — see `codeflow.md` for the settled explanation.
- No code changes in this pass — purely explanatory, in service of the project's actual goal (deep Kafka/architecture understanding, not just a finished app).

---

*New entries go at the top. Each entry should say what changed and, where it's a decision rather than a code change, point to the relevant section of `decision.md` instead of duplicating the reasoning here.*
