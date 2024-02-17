-- Insertar categorías
INSERT INTO CATEGORY (NAME) VALUES ('Electrónica');
INSERT INTO CATEGORY (NAME) VALUES ('Ropa');
INSERT INTO CATEGORY (NAME) VALUES ('Hogar');
INSERT INTO CATEGORY (NAME) VALUES ('Deportes');
INSERT INTO CATEGORY (NAME) VALUES ('Alimentación');
INSERT INTO CATEGORY (NAME) VALUES ('Juguetes');

-- Insertar clientes
INSERT INTO CUSTOMER (first_name,last_name, EMAIL,password,address,phone_number) VALUES ('Jorge', 'Hernandez', 'Jorge@.com', 'pepito12', 'Calle santacruz 123','63475345');
INSERT INTO CUSTOMER (first_name,last_name, EMAIL,password,address,phone_number) VALUES ('Eustebio', 'Carrasco', 'Eustebioss@example.com', 'Eust645', 'Calle Penelope 123','645632224');
-- Insertar wishlist
insert into wishlist (wishlist_name, customer_id) values ('Wishlist1',1);

-- Insertar productos ahora con wishlist
INSERT INTO PRODUCT (sku, description, price, stock) VALUES ('SKU001', 'Smartphone avanzado', 699.99, 50);
INSERT INTO PRODUCT (sku, description, price, stock) VALUES ('SKU002', 'Camiseta de algodón', 19.99, 100);
INSERT INTO PRODUCT (sku, description, price, stock) VALUES ('SKU003', 'Sofá cómodo para salón', 899.99, 10);
INSERT INTO PRODUCT (sku, description, price, stock) VALUES ('SKU004', 'Zapatillas para correr', 79.99, 30);
INSERT INTO PRODUCT (sku, description, price, stock) VALUES ('SKU005', 'Arroz integral', 2.99, 200);
INSERT INTO PRODUCT (sku, description, price, stock) VALUES ('SKU006', 'Muñeca para niños', 29.99, 50);
INSERT INTO PRODUCT (sku, description, price, stock) VALUES ('SKU007', 'Portátil ligero', 1299.99, 20);
INSERT INTO PRODUCT (sku, description, price, stock) VALUES ('SKU008', 'Chaqueta de invierno', 59.99, 50);
INSERT INTO PRODUCT (sku, description, price, stock) VALUES ('SKU009', 'Mesa de comedor', 349.99, 15);
INSERT INTO PRODUCT (sku, description, price, stock) VALUES ('SKU010', 'Balón de fútbol', 14.99, 100);

-- Asociar productos con categoría
INSERT INTO PRODUCT_CATEGORY (product_id, category_id) VALUES (1, 1), (1, 4);
INSERT INTO PRODUCT_CATEGORY (product_id, category_id) VALUES (7, 1), (7, 4);
INSERT INTO PRODUCT_CATEGORY (product_id, category_id) VALUES (2, 2), (2, 4);
INSERT INTO PRODUCT_CATEGORY (product_id, category_id) VALUES (8, 2), (8, 4);
INSERT INTO PRODUCT_CATEGORY (product_id, category_id) VALUES (3, 3), (3, 1);
INSERT INTO PRODUCT_CATEGORY (product_id, category_id) VALUES (9, 3), (9, 1);
INSERT INTO PRODUCT_CATEGORY (product_id, category_id) VALUES (4, 4);
INSERT INTO PRODUCT_CATEGORY (product_id, category_id) VALUES (5, 5);
INSERT INTO PRODUCT_CATEGORY (product_id, category_id) VALUES (6, 6);
INSERT INTO PRODUCT_CATEGORY (product_id, category_id) VALUES (10, 4), (10, 5);

-- Insertar productos en carrito
INSERT INTO CART (quantity, product_id, customer_id) VALUES (2, 1, 1);
INSERT INTO CART (quantity, product_id, customer_id) VALUES (1, 2, 1);
INSERT INTO CART (quantity, product_id, customer_id) VALUES (3, 3, 2);
INSERT INTO CART (quantity, product_id, customer_id) VALUES (4, 4, 1);
INSERT INTO CART (quantity, product_id, customer_id) VALUES (1, 5, 1);
INSERT INTO CART (quantity, product_id, customer_id) VALUES (2, 6, 2);
INSERT INTO CART (quantity, product_id, customer_id) VALUES (1, 7, 1);
INSERT INTO CART (quantity, product_id, customer_id) VALUES (1, 8, 2);
INSERT INTO CART (quantity, product_id, customer_id) VALUES (1, 9, 1);
INSERT INTO CART (quantity, product_id, customer_id) VALUES (1, 10, 2);



