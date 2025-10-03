create table refresh_tokens (
                                id bigint auto_increment primary key,
                                user_id bigint not null,
                                token varchar(255) not null unique,
                                created_at timestamp not null default current_timestamp,
                                expires_at timestamp not null,
                                revoked boolean not null,
                                device_info varchar(255) not null,
                                ip_address varchar(100) not null,
                                constraint fk_refresh_token_user foreign key (user_id) references users(id)
);