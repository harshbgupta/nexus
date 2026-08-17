# Phase 4 — Expert: Production Operations

Status: not started.

## 4.1 — Cluster sizing
**Plan:** Go from 1 broker to 3 in Docker Compose, replication factor 3, `min.insync.replicas=2`.

**Notes:** _Not started._

## 4.2 — Broker failure drills
**Plan:** Kill a broker mid-traffic, watch leader election and ISR shrink/grow.

**Notes:** _Not started._

## 4.3 — Log compaction
**Plan:** A compacted "latest rate per metal" topic — understand tombstones.

**Notes:** _Not started._

## 4.4 — Quotas
**Plan:** Throttle a producer/consumer deliberately, understand why multi-tenant clusters need this.

**Notes:** _Not started._

## 4.5 — Observability
**Plan:** JMX exporter + Prometheus + Grafana dashboard for lag and broker health.

**Notes:** _Not started._

## 4.6 — KRaft internals
**Plan:** The Raft-based metadata quorum, mostly conceptual plus config inspection (not a full multi-controller lab).

**Notes:** _Not started._

## 4.7 — Kafka on Kubernetes
**Plan:** Migrate the local cluster to Strimzi, deployed into the same `nexus` namespace as everything else.

**Notes:** _Not started._

## 4.8 — Landscape comparison
**Plan:** Kafka vs. Pulsar vs. Redpanda vs. SQS/Kinesis, and why you'd pick each.

**Notes:** _Not started._

---
**Previous:** [Phase 3 — Advanced](phase-3-advanced.md)
**Next:** [Phase 5 — Interview prep](phase-5-interview-prep.md)
