create table customer_addresses(

    id bigint not null auto_increment,
    street varchar(100) not null,
    number varchar(10),
    complement varchar(50),
    city varchar(100),
    state varchar(100),
    uf char(2),
    cep char(8),
    customer_id bigint,
    primary key(id),
    CONSTRAINT fk_customers_addresses_customers_id FOREIGN KEY (customer_id) REFERENCES customers(id)

);