create table product_categories(

    id bigint not null auto_increment,
    name varchar(100) not null,
    description varchar(200),
    image_url varchar(300),
    primary key (id)
);