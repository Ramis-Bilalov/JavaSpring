DROP TABLE IF EXISTS basket;
CREATE TABLE basket (
                          id SERIAL PRIMARY KEY,
                          title VARCHAR(255) UNIQUE NOT NULL,
                          description TEXT,
                          price BIGINT
);