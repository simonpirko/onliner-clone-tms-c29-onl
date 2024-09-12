CREATE TABLE account
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255),
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    type     VARCHAR(50)  NOT NULL,
    role     VARCHAR(50)  NOT NULL
);

CREATE TABLE product
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255),
    description TEXT,
    category    VARCHAR(100),
    image       VARCHAR(255)
);

CREATE TABLE cart
(
    id         BIGSERIAL PRIMARY KEY,
    account_id BIGINT,
    FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE
);

CREATE TABLE cart_item
(
    id         BIGSERIAL PRIMARY KEY,
    product_id BIGINT,
    account_id BIGINT,
    quantity   INTEGER,
    price      DECIMAL(10, 2),
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE,
    FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE
);

CREATE TABLE orderItem
(
    id         BIGSERIAL PRIMARY KEY,
    product_id BIGINT,
    quantity   INTEGER,
    price      DECIMAL(10, 2),
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);

CREATE TABLE "order"
(
    id           BIGSERIAL PRIMARY KEY,
    orderItem_id BIGINT,
    totalPrice   DECIMAL(10, 2),
    status       VARCHAR(50),
    FOREIGN KEY (orderItem_id) REFERENCES orderItem (id) ON DELETE CASCADE
);

CREATE TABLE shop
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255),
    description TEXT,
    creator_id  BIGINT,
    FOREIGN KEY (creator_id) REFERENCES account (id) ON DELETE CASCADE
);