create table users
(
    name     text not null,
    password text,
    login    text not null
        constraint users_pk
            primary key
);

alter table users
    owner to postgres;

create table role
(
    id        integer not null
        constraint key_name
            primary key,
    role_name text
);

alter table role
    owner to postgres;

create table user_roles
(
    user_login text    not null,
    role_id    integer not null
);

alter table user_roles
    owner to postgres;

insert into public.role (id, role_name)
values  (1, 'Admin'),
        (2, 'Operator'),
        (3, 'Analyst');

