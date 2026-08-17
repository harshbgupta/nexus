# Phase 1 — Novice: Core Mechanics

Status: not started.

## 1.1 — Local cluster
**Plan:** Docker Compose, Kafka in KRaft mode (no ZooKeeper), single broker, + Kafka UI.

**Notes:** _Not started._

## 1.2 — CLI fluency
**Plan:** `kafka-topics.sh`, console producer/consumer, `kafka-consumer-groups.sh` — pure CLI, deliberately before any Java, so "Kafka" and "Spring Kafka" don't get conflated.

**Notes:** _Not started._

## 1.3 — First real producer
**Plan:** Wire `pricing-service` to publish a `MetalRateUpdated` event to `nexus.pricing.rate-updated` whenever `CreateMetalRateService` runs.

**Notes:** _Not started._

## 1.4 — First real consumer
**Plan:** A bare listener logging every message on that topic, watching offsets move in Kafka UI.

**Notes:** _Not started._

## 1.5 — Multiple consumer groups, one topic
**Plan:** Add a second group (in `notification-service`) on the same topic → see fan-out (every group gets every message) vs. load-balancing (instances within a group split the work).

**Notes:** _Not started._

## 1.6 — Partitioning & keys
**Plan:** Bump the topic to 3 partitions, key by metal (GOLD/SILVER/PLATINUM), scale `catalog-service`'s consumer to multiple instances, watch partition assignment and per-key ordering.

**Notes:** _Not started._

## 1.7 — Rebalancing live
**Plan:** Kill a consumer instance mid-stream, watch Kafka UI show the rebalance and partition reassignment happen.

**Notes:** _Not started._

---
**Previous:** [Phase 0 — Foundations](phase-0-foundations.md)
**Next:** [Phase 2 — Intermediate](phase-2-intermediate.md)
