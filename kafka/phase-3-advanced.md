# Phase 3 — Advanced: Stream Processing & Integration

Status: not started.

## 3.1 — Kafka Streams intro
**Plan:** New `analytics-service`, a `KStream` reading `orders.placed`.

**Notes:** _Not started._

## 3.2 — Stateless transforms
**Plan:** map/filter/branch (e.g. isolate high-value orders).

**Notes:** _Not started._

## 3.3 — Stateful aggregation
**Plan:** Windowed orders/minute and revenue/minute via `KTable` + state store.

**Notes:** _Not started._

## 3.4 — Streams-table join
**Plan:** Join `orders.placed` (stream) with `pricing.rate-updated` (table) to compute live enriched order value.

**Notes:** _Not started._

## 3.5 — Exactly-once in Streams
**Plan:** `processing.guarantee=exactly_once_v2` — understand precisely what it does and doesn't cover.

**Notes:** _Not started._

## 3.6 — Kafka Connect
**Plan:** JDBC sink connector piping `orders.placed` into a reporting table.

**Notes:** _Not started._

## 3.7 — Debezium CDC (bonus)
**Plan:** Capture changes directly from `order-service`'s Postgres table as an alternate event source, compare to app-level publishing.

**Notes:** _Not started._

## 3.8 — ksqlDB, lightly
**Plan:** One or two SQL-style stream queries, mainly so you know the tool exists and when you'd reach for it.

**Notes:** _Not started._

---
**Previous:** [Phase 2 — Intermediate](phase-2-intermediate.md)
**Next:** [Phase 4 — Expert ops](phase-4-expert-ops.md)
