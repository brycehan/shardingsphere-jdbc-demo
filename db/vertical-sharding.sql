-- 垂直分片，数据库垂直分库
-- 删除数据库
drop database if exists bryce_user;
drop database if exists bryce_order;

-- 创建 bryce_user 数据库
create database bryce_user;
use bryce_user;

create table t_user (
    id bigint,
    uname varchar(30),
    primary key (id)
);

INSERT INTO t_user(id, uname) VALUES(1, 'brycehan');

-- 创建 bryce_order 数据库
create database bryce_order;
use bryce_order;
create table t_order (
     id bigint,
     order_no varchar(30),
     user_id bigint,
     amount decimal(10,2),
     primary key(id)
);