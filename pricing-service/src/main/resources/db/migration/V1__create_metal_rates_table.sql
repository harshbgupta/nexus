CREATE TABLE metal_rates (
    id UUID PRIMARY KEY,
    metal VARCHAR(32) NOT NULL,
    purity VARCHAR(16) NOT NULL,
    rate_per_gram NUMERIC(10, 2) NOT NULL,
    effective_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL
);
