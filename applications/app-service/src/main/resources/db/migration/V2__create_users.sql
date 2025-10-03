create table users (
                       id bigint auto_increment primary key,
                       first_name varchar(100) not null,
                       last_name varchar(100) not null,
                       email varchar(150) unique,
                       password varchar(255) not null,
                       role_id bigint not null,
                       phone_number varchar(50) unique,
                       identification varchar(50) unique,
                       birth_date date,
                       profile_picture varchar(255),
                       created_at timestamp not null default current_timestamp,
                       deleted boolean not null default false,
                       constraint fk_user_role foreign key (role_id) references roles(id)
);
