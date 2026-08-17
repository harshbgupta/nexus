# Kafka Learning Track — Nexus

Learning Kafka in depth is the primary goal of this track; Nexus (the jewellery-commerce microservices project) is the hands-on scaffolding, not the deliverable. Every stage below is done against real Nexus services — no toy examples.

**Workflow:** Kafka producer/consumer/topic code is written by hand in the IDE (copy-pasted from chat, not written by Claude) so it actually sticks. Everything else in this repo (services, Postgres, Docker, K8s) was scaffolded directly. These notes are kept up to date after each stage — see the phase files for the actual content.

## The Plan

The curriculum runs novice → expert across five phases, each one grounded in real Nexus services rather than toy examples — by the end, every core Kafka concept has been exercised against actual code in this repo, not a throwaway demo. Interview readiness is the actual goal; the project is the practice ground, not the deliverable.

**Phase 0 — Foundations.** Pure concepts, no code: why Kafka's log-based model exists at all (vs. traditional queues, vs. plain REST between services), the three delivery-semantics guarantees (at-most-once / at-least-once / exactly-once), and the core vocabulary (topic, partition, offset, broker, consumer group, replication). Everything after this point assumes these words already mean something.

**Phase 1 — Novice: core mechanics.** Stand up a local Kafka cluster and Kafka UI, get fluent on the CLI before touching Java, then wire the first real producer/consumer into Nexus (`pricing-service` publishing rate updates). From there: multiple consumer groups on one topic (fan-out vs. load-balancing), partitioning and keys, and watching a live rebalance happen. This phase is where "topic," "partition," and "consumer group" stop being definitions and become things you've directly observed.

**Phase 2 — Intermediate: the real order pipeline.** Wire `order-service` → `inventory-service` → `payment-service` → `notification-service` into a fully event-driven pipeline with zero direct REST calls between them. Then go deeper on correctness: offset commit strategies and duplicate delivery, idempotent producers and acks, retry topics and dead-letter queues, poison-pill messages, and Avro + Schema Registry with schema evolution. This is the phase where "at-least-once" from Phase 0 stops being theory.

**Phase 3 — Advanced: stream processing & integration.** A new `analytics-service` built on Kafka Streams — stateless transforms, windowed aggregation, and a stream-table join (live orders enriched with live gold/silver rates). Then Kafka Connect (a JDBC sink), an optional Debezium CDC exercise reading straight from `order-service`'s own database, and light exposure to ksqlDB.

**Phase 4 — Expert: production operations.** Scale the local cluster to 3 brokers, run broker-failure drills, set up log compaction, quotas, and full observability (JMX + Prometheus + Grafana). Learn KRaft's internals, migrate the cluster to Kubernetes via Strimzi, and finish with a deliberate comparison against Pulsar, Redpanda, and cloud-native alternatives (SQS/Kinesis) — knowing *why* you'd pick Kafka is itself an interview topic.

**Phase 5 — Interview prep.** Not a separate track — checkpoints threaded through the other four phases: a terminology drill after Phase 1, system-design mocks after Phases 2 and 3, a full ops-scenario mock after Phase 4, and an ongoing debugging-story log built from real incidents deliberately triggered along the way (the retry/DLQ work, the poison-pill message, the broker-kill drill) — real stories instead of rehearsed ones.

## Progress

- [x] **Phase 0 — Foundations** — [phase-0-foundations.md](phase-0-foundations.md)
- [ ] **Phase 1 — Novice: core mechanics** — [phase-1-core-mechanics.md](phase-1-core-mechanics.md)
- [ ] **Phase 2 — Intermediate: the real order pipeline** — [phase-2-intermediate.md](phase-2-intermediate.md)
- [ ] **Phase 3 — Advanced: stream processing & integration** — [phase-3-advanced.md](phase-3-advanced.md)
- [ ] **Phase 4 — Expert: production operations** — [phase-4-expert-ops.md](phase-4-expert-ops.md)
- [ ] **Phase 5 — Interview prep checkpoints** — [phase-5-interview-prep.md](phase-5-interview-prep.md)

## Full stage list (35 stages)

### Phase 0 — Foundations
- [x] 0.1 Why Kafka exists
- [x] 0.2 Delivery semantics vocabulary
- [x] 0.3 Core vocabulary (topic/partition/offset/broker/consumer group/...)

### Phase 1 — Novice: core mechanics
- [ ] 1.1 Local cluster — Docker Compose (KRaft) + Kafka UI
- [ ] 1.2 CLI fluency — topics, console producer/consumer, consumer-groups.sh
- [ ] 1.3 First real producer — `pricing-service` publishes `MetalRateUpdated`
- [ ] 1.4 First real consumer — bare listener logging the topic
- [ ] 1.5 Multiple consumer groups, one topic — fan-out vs. load-balancing
- [ ] 1.6 Partitioning & keys — scale `catalog-service` consumers
- [ ] 1.7 Rebalancing live — kill a consumer instance mid-stream

### Phase 2 — Intermediate: the real order pipeline
- [ ] 2.1 Design event contracts (`OrderPlaced`, `InventoryReserved`/`OutOfStock`, `PaymentCompleted`/`Failed`)
- [ ] 2.2 `order-service` → producer
- [ ] 2.3 `inventory-service` → consumer + producer
- [ ] 2.4 `payment-service` → consumer + producer
- [ ] 2.5 `notification-service` → pure consumer (full pipeline event-driven)
- [ ] 2.6 Offset commit strategies (auto vs. manual, duplicate delivery)
- [ ] 2.7 Idempotent producers & acks (`enable.idempotence`, `acks=all`, `min.insync.replicas`)
- [ ] 2.8 Failure handling — retry topic + DLQ on `payment-service`
- [ ] 2.9 Poison-pill messages
- [ ] 2.10 Schema Registry + Avro
- [ ] 2.11 Schema evolution (backward compatibility)
- [ ] 2.12 Consumer lag under load

### Phase 3 — Advanced: stream processing & integration
- [ ] 3.1 Kafka Streams intro — `analytics-service`
- [ ] 3.2 Stateless transforms (map/filter/branch)
- [ ] 3.3 Stateful aggregation (windowed orders/minute, revenue/minute)
- [ ] 3.4 Stream-table join (orders × live rates)
- [ ] 3.5 Exactly-once in Streams
- [ ] 3.6 Kafka Connect — JDBC sink connector
- [ ] 3.7 Debezium CDC (bonus)
- [ ] 3.8 ksqlDB, lightly

### Phase 4 — Expert: production operations
- [ ] 4.1 Cluster sizing — 3 brokers, replication factor 3
- [ ] 4.2 Broker failure drills
- [ ] 4.3 Log compaction
- [ ] 4.4 Quotas
- [ ] 4.5 Observability — JMX + Prometheus + Grafana
- [ ] 4.6 KRaft internals
- [ ] 4.7 Kafka on Kubernetes — Strimzi
- [ ] 4.8 Landscape comparison (Kafka vs. Pulsar vs. Redpanda vs. SQS/Kinesis)

### Phase 5 — Interview prep checkpoints
- [ ] After Phase 1: terminology drill
- [ ] After Phase 2: mock — "design a notification system with Kafka"
- [ ] After Phase 3: mock — "design an order pipeline with exactly-once guarantees"
- [ ] After Phase 4: mock — ops scenarios (hot partition, lag spike, broker down)
- [ ] Ongoing: debugging-story log (real incidents from 2.8, 2.9, 4.2)
