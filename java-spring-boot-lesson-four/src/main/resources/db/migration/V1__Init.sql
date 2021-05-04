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
       ('iMac 24', 'With great power comes great capability.', 169990),
       ('iPad Pro', 'Faster performance and graphics.', 106990),
       ('iPad Air', 'The notepad where your best ideas are born.', 55990),
       ('iPad Mini', 'Mini just got mightier.', 37990),
       ('iPhone 12 Pro', 'A14 Bionic is the first 5-nanometer chip in the industry.', 99990),
       ('iPhone 12', 'A14 Bionic. Dual camera.', 79990),
       ('iPhone 12mini', 'A14 Bionic. Small design.', 69990),
       ('iPhone SE', 'iPhone SE packs our powerful A13 Bionic chip.', 39990),
       ('iPhone 11', 'A13 Bionic.', 54990),
       ('iPhone XR', 'A12 Bionic.', 47990),
       ('Apple Watch Series 6', 'The future of health is on your wrist.', 36990),
       ('Apple Watch SE', 'Heavy on features. Light on price.', 24990),
       ('Apple Watch 3', 'Good things come in 3.', 17990),
       ('AirPods', 'Magic runs in the family.', 19990),
       ('AirPods Pro', 'Active Noise Cancellation for immersive sound.', 24990),
       ('Apple TV 4K', 'A higher definition of TV.', 16990),
       ('Apple TV HD', 'Small cost.', 13990),
       ('Ipod Touch', 'The new iPod touch', 18990);

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