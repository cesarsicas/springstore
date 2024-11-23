create table customers(

    id bigint not null auto_increment,
    name varchar(100) not null,
    document varchar(50),
    user_id bigint,
    CONSTRAINT fk_customers_users_id FOREIGN KEY (user_id) REFERENCES users(id),

    primary key(id)

);