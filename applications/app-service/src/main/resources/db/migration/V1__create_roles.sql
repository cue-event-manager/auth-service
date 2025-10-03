create table roles (
                       id bigint auto_increment primary key,
                       name varchar(100) not null unique,
                       description varchar(255) not null,
                       created_at timestamp not null default current_timestamp
);
