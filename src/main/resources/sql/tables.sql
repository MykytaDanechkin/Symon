create table if not exists users
(
    id       bigserial primary key,
    username text unique not null,
    email    text unique not null,
    role text not null default 'USER',
    password text,
    avatar   text
)