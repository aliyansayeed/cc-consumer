CREATE TABLE transactions (
                              transaction_id SERIAL PRIMARY KEY,
                              card_number VARCHAR(19) NOT NULL,
                              amount NUMERIC(10, 2) NOT NULL,
                              timestamp TIMESTAMPTZ NOT NULL,
                              merchant_id VARCHAR(50) NOT NULL
);