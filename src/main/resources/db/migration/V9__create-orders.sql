create table orders(
    id bigint not null auto_increment,
    primary key(id),

    total_amount decimal(10,2),
    datetime datetime,

    customer_id bigint,
    CONSTRAINT fk_orders_customers FOREIGN KEY (customer_id) REFERENCES customers(id)

);