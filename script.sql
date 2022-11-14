create table users
(
    name     varchar(80)  not null
        primary key,
    password varchar(256) not null
);

alter table users
    owner to postgres;


