create table product_categories(

    id bigint not null auto_increment,
    name varchar(100) not null,
    description varchar(200),
    image_url varchar(300),
    primary key (id)
);

--
--create table products(
--
--    id bigint not null auto_increment,
--    name varchar(100) not null,
--    description varchar(500),
--    image_url varchar(300),
--    value decimal(10,2),
--    quantity int,
--    primary key(id),
--
--    merchant_id bigint,
--    CONSTRAINT fk_products_merchant_id FOREIGN KEY (merchant_id) REFERENCES merchants(id),
--
--    category_id bigint,
--    CONSTRAINT fk_products_product_categories FOREIGN KEY (category_id) REFERENCES product_categories(id)
--
--);