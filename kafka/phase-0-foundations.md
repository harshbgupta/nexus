# Phase 0 — Foundations

Pure concepts, no code. The goal is to have the vocabulary and the "why" settled before Stage 1.1 touches a terminal.

## 0.1 — Why Kafka Exists

**The problem, without Kafka:** `order-service` places an order and needs `inventory-service` to reserve stock, `payment-service` to charge, `notification-service` to alert the customer. The naive approach — direct REST calls chained together — has real problems at scale:

- **Tight coupling** — `order-service` has to know the address/API of every downstream service.
- **Synchronous blocking** — the whole chain waits on the slowest link.
- **Cascading failure** — one service being down can break the whole flow unless you build retry/circuit-breaking by hand, service by service.
- **No replay** — if a service crashes and misses an event, that information is just gone.

Traditional message queues (RabbitMQ, ActiveMQ, SQS) decouple producer and consumer in *time*, but they're **point-to-point and destructive** — once a consumer reads and acks a message, it's deleted. Two services wanting the same event need two separate queues, and a new service added later can't see history.

**Kafka's actual innovation is the log.** A topic is an **append-only, ordered, immutable sequence of events**, retained for a configured period regardless of who's read it. Consumers don't "take" messages off — they read from wherever they are in the log, at their own pace, and the message stays for anyone else too.

This is why:
- Multiple independent services can all read the *same* topic, each at their own position, without stepping on each other.
- A new service can start reading from the beginning and replay history.
- Producers and consumers don't know about each other at all.

**Analogy:** a traditional queue is a phone call — one sender, one receiver, gone once it's over. Kafka is a broadcast recording — anyone can tune in, at any point, and rewind, because the recording is the source of truth, not the act of delivery.

This is why Kafka is "a distributed commit log," not "a message queue" — and it's the reason partitioning, offsets, and consumer groups (0.3) are shaped the way they are.

## 0.2 — Delivery Semantics Vocabulary

What guarantee do you actually have that a message got processed?

- **At-most-once** — sent once, and if something goes wrong, it's lost — never redelivered. Sometimes correct on purpose (e.g. a "typing…" indicator). Achieved by not tracking acknowledgment, or committing an offset *before* processing.
- **At-least-once** — guaranteed to arrive, but might arrive **more than once**. Kafka's default safe posture: if a consumer crashes after processing but before committing its offset, it re-reads that message on restart. Requires idempotent consumer logic (processing the same event twice shouldn't double-charge a customer).
- **Exactly-once** — processed once, no duplicates, no loss. Hard, because "did I finish processing AND record that I finished" isn't atomic unless you go out of your way to make it so (idempotent producers + transactions — Stage 2.7 / 3.5). Also scoped: "exactly-once" in Kafka means exactly-once *within Kafka*, not necessarily for an external side effect like an actual bank charge.

Every later design decision is really a decision about which of these three you're choosing, on purpose.

## 0.3 — Core Vocabulary

| Term | Meaning |
|---|---|
| **Topic** | A named category of events (e.g. `nexus.orders.placed`). Logically one stream, physically split into partitions. |
| **Partition** | A topic split into ordered, append-only logs. Order guaranteed *within* a partition, never across partitions of the same topic. Unit of parallelism. |
| **Offset** | A message's position within its partition — an increasing integer. A consumer's progress is "the last offset I've committed." |
| **Broker** | A single Kafka server. A cluster is a group of brokers. |
| **Producer** | Anything that writes events to a topic. |
| **Consumer** | Anything that reads events from a topic. |
| **Consumer Group** | A named set of consumers sharing the work of reading a topic — each partition is read by exactly one consumer *within* a group. **Every distinct consumer group gets its own full copy of every message.** |
| **Replication factor** | How many broker copies of each partition exist for durability. |
| **Leader / Follower / ISR** | One broker leads a partition (handles all reads/writes); followers replicate it. In-Sync Replicas (ISR) are followers caught up enough to take over if the leader dies. |
| **Retention** | How long a topic keeps messages regardless of consumption — time-based, size-based, or (compacted topics, Stage 4.3) "latest value per key forever." |

## Self-check

1. If `inventory-service` and `notification-service` are two separate consumer groups on `orders.placed`, and one order comes in, how many times does that message get delivered in total?
   <details><summary>Answer</summary>Twice — once per consumer group. Each group gets its own full copy of every message, independent of the other group.</details>

2. If `catalog-service` runs 3 instances all in the *same* consumer group, reading a topic with 3 partitions, how does the work split?
   <details><summary>Answer</summary>One partition per instance — Kafka assigns each partition to exactly one consumer within a group, so with equal counts each instance handles exactly one partition's worth of traffic.</details>

3. Why does Kafka guarantee order *within* a partition but not *across* partitions of the same topic?
   <details><summary>Answer</summary>Because a partition is a single physical append-only log with one writer sequence and one reader offset — ordering is a property of that single log. Across partitions there's no shared sequence, so there's nothing to guarantee order against; that's also exactly what makes partitions independently parallelizable.</details>

---
**Next:** [Phase 1 — Novice: core mechanics](phase-1-core-mechanics.md), starting with Stage 1.1 (local Kafka cluster via Docker Compose).
