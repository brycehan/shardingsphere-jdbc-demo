create database if not exists bryce_readwrite_ds;
use bryce_readwrite_ds;

create table t_user (
    id bigint auto_increment,
    uname varchar(30),
    primary key (id)
);

INSERT INTO t_user(uname) VALUES('brycehan');