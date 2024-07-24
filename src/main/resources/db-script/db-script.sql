CREATE TABLE customer
(
    id      VARCHAR(15) PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    address VARCHAR(500) NOT NULL
);

CREATE TABLE contact
(
    customer_id VARCHAR(15) NOT NULL,
    contact     VARCHAR(13) PRIMARY KEY,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE `user`
(
    username VARCHAR(100) PRIMARY KEY,
    name     VARCHAR(200) NOT NULL,
    password VARCHAR(200) NOT NULL
);

CREATE TABLE item
(
    code  VARCHAR(50) PRIMARY KEY,
    name  VARCHAR(100)  NOT NULL,
    stock INT           NOT NULL,
    price DECIMAL(9, 2) NOT NULL
);

CREATE TABLE `order`
(
    id          VARCHAR(10) PRIMARY KEY,
    date        DATE         NOT NULL,
    username    VARCHAR(100) NOT NULL,
    customer_id VARCHAR(15)  NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (username) REFERENCES `user` (username),
    CONSTRAINT fk_customer_order FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE order_detail
(
    order_id  VARCHAR(10)   NOT NULL,
    item_code VARCHAR(50)   NOT NULL,
    price     DECIMAL(9, 2) NOT NULL,
    discount  INT           NOT NULL,
    CONSTRAINT PRIMARY KEY (order_id, item_code),
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES `order` (id),
    CONSTRAINT fk_item FOREIGN KEY (item_code) REFERENCES item (code)
);