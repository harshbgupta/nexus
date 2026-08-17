CREATE TABLE catalog_items (
    id UUID PRIMARY KEY,
    sku VARCHAR(64) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(64) NOT NULL,
    metal VARCHAR(32) NOT NULL,
    purity VARCHAR(16) NOT NULL,
    gross_weight_grams NUMERIC(10, 3) NOT NULL,
    making_charge_percent NUMERIC(5, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL
);
