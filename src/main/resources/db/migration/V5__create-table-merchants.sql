create table merchants(

    id bigint not null auto_increment,
    responsible_name varchar(100) not null,
    company_name varchar(200),
    document varchar(30),
    address varchar(300),
    user_id bigint,
    primary key(id),
    CONSTRAINT fk_merchants_users FOREIGN KEY (user_id) REFERENCES users(id)
);