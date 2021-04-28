BEGIN;


DROP TABLE IF EXISTS customers_products CASCADE;
CREATE TABLE customers_products (id serial PRIMARY KEY,
                                    customer_id serial,
                                    product_id serial,
                                    FOREIGN KEY (customer_id) REFERENCES customers(id),
                                    FOREIGN KEY (product_id) REFERENCES products(id));

INSERT INTO customers_products (customer_id, product_id) values (1, 2), (2, 1), (3, 1), (3, 2);

DROP TABLE IF EXISTS manufacturers CASCADE;
CREATE TABLE manufacturers (
    id serial PRIMARY KEY,
    title VARCHAR(255));
INSERT INTO manufacturers (title) VALUES ('Coca-Cola Company'), ('Danone');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (
    id serial PRIMARY KEY,
    title VARCHAR(255),
    price numeric(8, 2),
    customer_id int,
    manufacturer_id int,
    FOREIGN KEY (manufacturer_id) references manufacturers(id)
    );

INSERT INTO products (title,
                      manufacturer_id,
                      price,
                      customer_id)
VALUES ('Sprite', 1, 30.00, 3),
       ('Fanta', 2, 40.00, 1),
       ('Yoga', 2, 100.00, 3);

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (
    id serial PRIMARY KEY,
    name VARCHAR(255),
    product_id int);
INSERT INTO customers (name, product_id) VALUES ('Jim', 1), ('John', 2), ('Mike', 3);


DROP TABLE IF EXISTS big_items CASCADE;
CREATE TABLE big_items (id serial PRIMARY KEY, val int, testCol int, version serial);
INSERT INTO big_items (val, testCol) VALUES (10, 20);

COMMIT;