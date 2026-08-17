# Phase 2 — Intermediate: The Real Order Pipeline, Done Correctly

Status: not started.

## 2.1 — Design the event contracts first
**Plan:** `OrderPlaced`, `InventoryReserved`/`OutOfStock`, `PaymentCompleted`/`Failed`, as JSON, before touching code.

**Notes:** _Not started._

## 2.2 — `order-service` → producer
**Plan:** Publish `OrderPlaced` to `nexus.orders.placed`, keyed by `orderId`.

**Notes:** _Not started._

## 2.3 — `inventory-service` → consumer + producer
**Plan:** Consume `orders.placed`, reserve stock, publish to `nexus.inventory.status`.

**Notes:** _Not started._

## 2.4 — `payment-service` → consumer + producer
**Plan:** Consume reserved-inventory events, simulate payment, publish to `nexus.payments.status`.

**Notes:** _Not started._

## 2.5 — `notification-service` → pure consumer
**Plan:** Consumes every terminal-status topic; full pipeline is now event-driven end to end, zero direct REST calls between these four services.

**Notes:** _Not started._

## 2.6 — Offset commit strategies
**Plan:** Compare auto-commit vs. manual ack, force a mid-processing crash, observe duplicate delivery firsthand.

**Notes:** _Not started._

## 2.7 — Idempotent producers & acks
**Plan:** `enable.idempotence`, `acks=all`, `min.insync.replicas` — understand what guarantee each buys.

**Notes:** _Not started._

## 2.8 — Failure handling
**Plan:** Inject a deliberate failure in `payment-service`, add a retry topic with backoff, then a dead-letter topic for permanent failures.

**Notes:** _Not started._

## 2.9 — Poison-pill messages
**Plan:** Send a deliberately malformed payload, watch the consumer choke, fix it with an error handler + DLQ.

**Notes:** _Not started._

## 2.10 — Schema Registry + Avro
**Plan:** Swap `orders.placed` from JSON to Avro, stand up a Schema Registry container, register the schema.

**Notes:** _Not started._

## 2.11 — Schema evolution
**Plan:** Add a new optional field to `OrderPlaced`, verify old consumers keep working (backward compatibility in practice, not just theory).

**Notes:** _Not started._

## 2.12 — Consumer lag under load
**Plan:** Generate a burst of orders, watch lag build in Kafka UI / `kafka-consumer-groups.sh`, understand what actually causes it.

**Notes:** _Not started._

---
**Previous:** [Phase 1 — Core mechanics](phase-1-core-mechanics.md)
**Next:** [Phase 3 — Advanced](phase-3-advanced.md)
