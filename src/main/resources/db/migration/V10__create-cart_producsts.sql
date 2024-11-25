create table cart_products(
    id bigint not null auto_increment,
    primary key(id),

    product_id bigint,
    CONSTRAINT fk_cart_products_products FOREIGN KEY (product_id) REFERENCES products(id),

    cart_id bigint,
    CONSTRAINT fk_cart_products_carts FOREIGN KEY (cart_id) REFERENCES carts(id)

);