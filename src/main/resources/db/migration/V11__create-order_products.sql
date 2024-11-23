create table order_products(
    id bigint not null auto_increment,
    primary key(id),

    product_value decimal(10,2),
    product_quantity int,

    product_id bigint,
    CONSTRAINT fk_order_products_products FOREIGN KEY (product_id) REFERENCES products(id),

    order_id bigint,
    CONSTRAINT fk_order_products_orders FOREIGN KEY (order_id) REFERENCES orders(id)

);