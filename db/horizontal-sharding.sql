-- 垂直分片，数据库垂直分库
-- 创建 bryce_user_ds 数据库
create database if not exists bryce_user_ds;
use bryce_user_ds;

create table t_user (
    id bigint,
    uname varchar(30),
    primary key (id)
);

INSERT INTO t_user(id, uname) VALUES(1, 'brycehan');

-- 创建 bryce_order_ds 数据库
create database if not exists bryce_order_ds;
use bryce_order_ds;
create table t_order (
     id bigint,
     order_no varchar(30),
     user_id bigint,
     amount decimal(10,2),
     primary key(id)
);