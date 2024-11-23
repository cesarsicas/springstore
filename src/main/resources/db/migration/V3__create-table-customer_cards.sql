create table customer_credit_cards(

    id bigint not null auto_increment,
    card_number varchar(16) not null,
    brand varchar(50),
    exp_date char(6),
    customer_id bigint,
    primary key(id),
    CONSTRAINT fk_customer_credit_cards_customers_id FOREIGN KEY (customer_id) REFERENCES customers(id)

);