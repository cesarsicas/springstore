create table users(

    id bigint not null auto_increment,
    login varchar(100) not null unique,
    password varchar(255) not null,
    role varchar(255) not null,
    is_active tinyint not null,
    primary key(id)

);