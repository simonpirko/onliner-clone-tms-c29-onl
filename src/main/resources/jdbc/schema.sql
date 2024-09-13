DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
CREATE TABLE if not exists account
(
    id       BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name     VARCHAR(255),
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    type     VARCHAR(50)         NOT NULL,
    role     VARCHAR(50)         NOT NULL
);

CREATE TABLE if not exists product
(
    main_product_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name            VARCHAR(255),
    description     TEXT,
    category        VARCHAR(100),
    image           VARCHAR(255)
);

CREATE TABLE if not exists orderitem
(
    main_orderitem_id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    product_id BIGINT,
    quantity   INTEGER,
    price      DECIMAL(10, 2),
    FOREIGN KEY (product_id) REFERENCES product (main_product_id) ON DELETE CASCADE
);

CREATE TABLE if not exists "order"
(
    main_order_id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    orderItem_id BIGINT,
    totalPrice   DECIMAL(10, 2),
    status       VARCHAR(50),
    FOREIGN KEY (orderItem_id) REFERENCES orderItem (main_orderitem_id) ON DELETE CASCADE
);

CREATE TABLE if not exists shop
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title       VARCHAR(255),
    description TEXT,
    creator_id  BIGINT,
    FOREIGN KEY (creator_id) REFERENCES account (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS sellerproduct
(
    product_id BIGINT,
    shop_id    BIGINT,
    price      DECIMAL(10, 2),
    FOREIGN KEY (product_id) REFERENCES product (main_product_id) ON DELETE CASCADE,
    FOREIGN KEY (shop_id) REFERENCES shop (id) ON DELETE CASCADE
)