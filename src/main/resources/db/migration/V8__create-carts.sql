create table carts(
    id bigint not null auto_increment,
    primary key(id),

    customer_id bigint,
    CONSTRAINT fk_carts_customers FOREIGN KEY (customer_id) REFERENCES customers(id)


);