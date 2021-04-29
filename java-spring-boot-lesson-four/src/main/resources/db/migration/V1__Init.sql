DROP TABLE IF EXISTS products;
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) UNIQUE NOT NULL,
    description TEXT,
    price BIGINT
);

INSERT INTO products (title, description, price)
VALUES ('MacBook Air', 'Small chip. Giant leap.', 89990),
       ('MacBook Pro 16', 'A big, beautiful workspace.', 274990),
       ('iPad Pro', 'Faster performance and graphics.', 106990);

-- тестовая таблица

DROP TABLE IF EXISTS items;
CREATE TABLE items (
    id SERIAL,
    title VARCHAR(40)
);

INSERT INTO items (title)
VALUES ('stone'),
       ('knife'),
       ('spoon');