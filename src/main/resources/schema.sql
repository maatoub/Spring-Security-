-- MySQL
-- File: schema-mysql.sql
-- Description: Database setup for MySQL

create table if not exists users (
  username varchar(50) not null primary key,
  password varchar(500) not null,
  enabled boolean not null
);

create table if not exists authorities (
  username varchar(50) not null,
  authority varchar(50) not null,
  constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index if not exists ix_auth_username on authorities (username, authority);

-- MySQL-specific table to support remembr-me token storage
/*create table persistent_logins (
    username varchar(64) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null
);
*/