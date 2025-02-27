alter table users
    add column if not exists role text not null default 'USER';

alter table profile
    add column if not exists email text not null unique default 'placeholder@gmail.com';