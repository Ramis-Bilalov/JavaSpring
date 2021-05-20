DROP TABLE IF EXISTS basket;
CREATE TABLE basket (
                          id SERIAL PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          description TEXT,
                          price BIGINT
);