# Code Flow

How a request actually moves through this codebase. Written so that if code gets added later without a live walkthrough, this document is enough to reconstruct what's happening and why it's organized this way.

## The four layers, and what belongs in each

Every business service (`user-service`, `catalog-service`, `pricing-service`, `inventory-service`, `order-service`, `payment-service`) follows the same internal shape:

```
domain/
├── model/       → plain Java classes (e.g. User, CatalogItem) — zero framework imports
└── port/
    ├── in/      → use-case interfaces (what the outside world can ask this service to do)
    └── out/     → interfaces for what the service needs from the outside world (persistence, hashing, tokens, later: messaging)

application/
└── usecase/     → concrete business logic, implements domain/port/in, depends on domain/port/out interfaces only

infrastructure/
├── persistence/ → JPA entities, Spring Data repositories, adapters implementing domain/port/out
├── security/    → crypto/JWT adapters implementing domain/port/out (where applicable)
└── config/      → framework-level bean wiring (e.g. SecurityConfig)

presentation/
└── rest/        → controllers, DTOs, exception advice
```

- **domain** — the business rules and the data shape. No Spring, no JPA, no HTTP — if you deleted every framework import from the whole project, `domain` would still compile.
- **application** — orchestration: validates rules, calls `port/out` interfaces in sequence, returns plain `domain` objects. Never sees a DTO or a JPA entity.
- **infrastructure** — where a `port/out` interface meets a real technology (Postgres via JPA, BCrypt, jjwt). Contains zero business rules — just translation.
- **presentation** — where an external trigger (HTTP today; Kafka listeners later) turns into a call on a `port/in` interface, and a `domain` object turns into a response DTO.

## The dependency rule (compile-time imports, verified — not just asserted)

```
infrastructure → domain
presentation   → domain
application    → domain
domain         → (nothing)
```

`infrastructure` and `presentation` never import each other, and neither imports `application`'s concrete classes directly. Confirmed by grepping actual imports (e.g. `UserController` never imports `LoginService`, only `LoginUseCase`).

**Why this matters in practice:** Spring's dependency injection is what closes the gap between an interface and its real implementation, at startup, via component scanning (`@Service`, `@Component`, `@RestController`) and constructor injection. No layer ever needs to know the concrete class on the other side of an interface — which is exactly what makes any of the technology choices (Postgres, BCrypt, REST) swappable without touching business logic.

**The one subtlety worth remembering:** at *runtime*, a request does travel through every layer in sequence (see trace below) — that's a real, true call chain. What's specifically not true is that this happens via direct class-to-class imports across `presentation` ↔ `infrastructure`. The connection is always mediated by an interface `domain` owns.

## Full request trace — `POST /api/v1/users/login` (user-service)

```
HTTP request
  → UserController.login(LoginRequest)                          [presentation]
    → LoginUseCase.login(email, password)                       [interface — domain/port/in]
      → LoginService.login(...)                                 [application — the real implementation]
        → UserRepositoryPort.findByEmail(email)                 [interface — domain/port/out]
          → UserRepositoryAdapter.findByEmail(...)               [infrastructure]
            → UserJpaRepository.findByEmail(...)                    [Spring Data → Postgres]
        → PasswordHasherPort.matches(raw, hash)                 [interface — domain/port/out]
          → BCryptPasswordHasherAdapter.matches(...)             [infrastructure]
        → TokenIssuerPort.issue(user)                           [interface — domain/port/out]
          → JwtTokenIssuerAdapter.issue(user)                    [infrastructure — signs the JWT]
    → LoginResponse(token)                                      [presentation — maps domain User/token → DTO]
  ← HTTP response
```

Every "interface" hop above is a point where the implementation could be swapped without touching the caller. That substitutability is the entire payoff of paying for this many files.

## The same shape, repeated across services

`catalog-service`, `pricing-service`, `inventory-service`, `order-service`, `payment-service` all follow the identical pattern — one primary aggregate (`CatalogItem`, `MetalRate`, `StockItem`, `Order`, `Payment`), one repository port + JPA adapter, one controller with create/getById/list endpoints. Once the `user-service` trace above makes sense, the others are the same shape with different nouns — see each service's own `domain/model` for its aggregate's fields.

## Kafka flow (not yet wired in)

Once Kafka enters the picture (Nexus Kafka curriculum, tracked under `../../kafka/`), two new kinds of classes will appear, and where they land matters:

- **A Kafka producer** (publishing an event as a side effect of a use case) is a `port/out` implementation — same role as `UserRepositoryAdapter` — lives in `infrastructure`.
- **A Kafka consumer** (`@KafkaListener` reacting to an event and calling into a use case) plays the same *role* as a controller — a driving adapter — even though it technically needs `spring-kafka`, a `infrastructure`-sounding dependency. Worth deciding deliberately (see `decision.md` → Open/deferred decisions) whether it's filed under `infrastructure` by technology, or a renamed `adapter/in`/`adapter/out` split by role, once this is actually being built.

This section gets filled in properly once Kafka Stage 2.2+ (the first real producer/consumer wiring into a Nexus service) happens.
