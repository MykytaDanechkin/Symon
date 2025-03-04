alter table users
    add column if not exists role text not null default 'USER';

alter table profile
    add column if not exists email text not null unique default 'placeholder@gmail.com';

alter table profile
    add column  if not exists badge text;

alter table profile
    add column if not exists reviews int default 0;

alter table show_review
    add column if not exists review_title text not null default 'honest review';

alter table show_review
    add column if not exists date timestamp not null default now();