# Nexus

Jewellery-commerce microservices platform built as a deep, hands-on Kafka learning project — Spring Boot, Clean Architecture, event-driven.

## About

Nexus is a jewellery e-commerce backend (Tanishq-style) built with Spring Boot and Spring Cloud. The project's actual purpose is **learning Kafka and event-driven architecture in depth** — the microservices below are the hands-on scaffolding that curriculum runs on top of, not the end goal. See [`kafka/README.md`](kafka/README.md) for the full learning plan and progress.

Every service (aside from `discovery-server` and `gateway`, which carry no business logic) follows **Clean Architecture** — `domain` / `application` / `infrastructure` / `presentation` — so swapping a technology choice (database, crypto library, messaging system) never touches business rules. See [`documentation/code/codeflow.md`](documentation/code/codeflow.md) for how a request actually flows through the layers, and [`documentation/code/decision.md`](documentation/code/decision.md) for the reasoning behind every architectural/scope decision made along the way.

## Services

| Service | Port | Database | Purpose |
|---|---|---|---|
| `discovery-server` | 8761 | — | Eureka service registry |
| `gateway` | 8080 | — | Spring Cloud Gateway, single entry point |
| `user-service` | 8081 | `nexus_user` | Registration, login, JWT issuance |
| `catalog-service` | 8082 | `nexus_catalog` | Jewellery items (sku, metal, purity, weight) |
| `pricing-service` | 8083 | `nexus_pricing` | Gold/silver rate records |
| `inventory-service` | 8084 | `nexus_inventory` | Stock per sku/branch |
| `order-service` | 8085 | `nexus_order` | Place/get/list orders |
| `payment-service` | 8086 | `nexus_payment` | Simulated payment processing |
| `notification-service` | 8087 | — | Stateless, logs notifications |

## Tech stack

- Java 21, Spring Boot 3.5.4, Spring Cloud 2025.0.0
- Maven multi-module (one parent `pom.xml`, one module per service)
- PostgreSQL (one shared instance locally, one database per service) + Flyway migrations
- Eureka for service discovery, Spring Cloud Gateway for routing
- Docker Compose for local infra, Kubernetes manifests for cluster deployment
- Kafka (event-driven pipeline — in progress, see [`kafka/`](kafka/))

## Project structure

```
nexus/
├── pom.xml                      # parent (packaging: pom)
├── discovery-server/
├── gateway/
├── user-service/                # Clean Architecture: domain/application/infrastructure/presentation
├── catalog-service/
├── pricing-service/
├── inventory-service/
├── order-service/
├── payment-service/
├── notification-service/
├── infra/
│   ├── docker/                  # docker-compose.yml + Postgres init script
│   └── k8s/                     # namespace, Postgres StatefulSet, per-service Deployment+Service
├── kafka/                       # Kafka learning curriculum, phase-by-phase notes and progress
└── documentation/code/          # decision.md, codeflow.md, changelog.md
```

## Getting started

**Run everything locally with Docker Compose** (builds every service + a shared Postgres instance):

```bash
cd nexus
docker compose -f infra/docker/docker-compose.yml up --build
```

Once up:
- Eureka dashboard: [http://localhost:8761](http://localhost:8761)
- API entry point (via gateway): [http://localhost:8080](http://localhost:8080)
- Each service is also reachable directly on its own port (table above) for local debugging.

**Build/verify without Docker:**

```bash
cd nexus
mvn -DskipTests compile
```

## Documentation

- [`kafka/README.md`](kafka/README.md) — the full Kafka learning curriculum (5 phases, 35 stages) and current progress
- [`documentation/code/decision.md`](documentation/code/decision.md) — every deliberate architecture/scope decision, with reasoning
- [`documentation/code/codeflow.md`](documentation/code/codeflow.md) — how a request flows through the code, layer by layer
- [`documentation/code/changelog.md`](documentation/code/changelog.md) — dated log of what's changed

## Status

Full microservices scaffolding (all 9 services, Postgres, Docker, Kubernetes) is built and verified. Kafka integration has not started yet — see [`kafka/README.md`](kafka/README.md) for the plan.
