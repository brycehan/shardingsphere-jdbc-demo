-- 数据库读写分离
-- 删除 bryce_readwrite 数据库
drop database if exists bryce_readwrite;

-- 创建 bryce_readwrite 数据库
create database bryce_readwrite;
use bryce_readwrite;

create table t_user (
    id bigint auto_increment,
    uname varchar(30),
    primary key (id)
);

INSERT INTO t_user(uname) VALUES('brycehan');