CREATE TABLE Product
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    name            VARCHAR(255),
    price           DOUBLE                NOT NULL,
    quantityInStock INT                   NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

INSERT INTO Product (id, name, price, quantityInStock) DEFAULT VALUES (1, "Product Test", 10.00, 15);