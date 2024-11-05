create table  product_categories (
    id bigint not null auto_increment,
    name varchar(100) not null,
    description varchar(300),
    primary key(id)
);

create table products(
    id bigint not null auto_increment,
    name varchar(100) not null,
    description varchar(300),
    value decimal,
    primary key(id),
    category_id bigint,
    CONSTRAINT fk_product_categories FOREIGN KEY (category_id) REFERENCES product_categories(id)

);