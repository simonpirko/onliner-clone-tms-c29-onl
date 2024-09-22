
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
    product_name            VARCHAR(255),
    product_description     TEXT,
    product_category        VARCHAR(100),
    product_image           VARCHAR(255)
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
    main_shop_id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    shop_title       VARCHAR(255),
    shop_description TEXT,
    creator_id  BIGINT,
    FOREIGN KEY (creator_id) REFERENCES account (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS sellerproduct
(
    seller_product_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    product_id BIGINT,
    shop_id    BIGINT,
    price      DECIMAL(10, 2),
    FOREIGN KEY (product_id) REFERENCES product (main_product_id) ON DELETE CASCADE,
    FOREIGN KEY (shop_id) REFERENCES shop (main_shop_id) ON DELETE CASCADE
)
