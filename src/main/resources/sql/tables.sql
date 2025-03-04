create table if not exists users
(
    id       bigserial primary key,
    username text unique not null,
    email    text unique not null,
    role     text        not null default 'USER',
    password text,
    avatar   text
);

create table if not exists profile
(
    id               bigint primary key references users (id) on delete cascade,
    username         text unique not null,
    profile_picture  text,
    email text not null unique,
    background_color text,
    main_color       text,
    secondary_color  text,
    following        int,
    followed         int
);

create table if not exists show
(
    id           bigserial primary key,
    title        text not null,
    description  text,
    genre        text,
    poster       text,
    director     text,
    rotten_score float,
    user_score   float
);

create table if not exists show_review
(
    id      bigserial primary key,
    user_id bigint not null references users (id) on delete cascade,
    show_id bigint not null references show (id) on delete cascade,
    review  text,
    score   float,
    date timestamp
);