CREATE TABLE stock_items (
    id UUID PRIMARY KEY,
    sku VARCHAR(64) NOT NULL,
    branch VARCHAR(128) NOT NULL,
    quantity_available INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    UNIQUE (sku, branch)
);
