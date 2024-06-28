-- 水平分片
-- 删除数据库
drop database if exists bryce_user;
drop database if exists bryce_order_0;
drop database if exists bryce_order_1;

-- 创建 bryce_user 数据库
create database bryce_user;
use bryce_user;

create table t_user (
    id bigint,
    uname varchar(30),
    primary key (id)
);
create table t_dict(
   id bigint,
   dict_type varchar(200),
   primary key(id)
);

-- 数据库水平分库，订单表水平分表
-- 创建 bryce_order_0 数据库
create database bryce_order_0;
use bryce_order_0;
create table t_order_0 (
  id bigint,
  order_no varchar(30),
  user_id bigint,
  amount decimal(10,2),
  primary key(id)
);
create table t_order_1 (
  id bigint,
  order_no varchar(30),
  user_id bigint,
  amount decimal(10,2),
  primary key(id)
);

create table t_order_item_0(
  id bigint,
  order_no varchar(30),
  user_id bigint,
  price decimal(10,2),
  `count` int,
  primary key(id)
);

create table t_order_item_1(
  id bigint,
  order_no varchar(30),
  user_id bigint,
  price decimal(10,2),
  `count` int,
  primary key(id)
);

create table t_dict(
    id bigint,
    dict_type varchar(200),
    primary key(id)
);
-- 创建 bryce_order_1 数据库
create database bryce_order_1;
use bryce_order_1;
create table t_order_0 (
   id bigint,
   order_no varchar(30),
   user_id bigint,
   amount decimal(10,2),
   primary key(id)
);
create table t_order_1 (
   id bigint,
   order_no varchar(30),
   user_id bigint,
   amount decimal(10,2),
   primary key(id)
);

create table t_order_item_0(
  id bigint,
  order_no varchar(30),
  user_id bigint,
  price decimal(10,2),
  `count` int,
  primary key(id)
);

create table t_order_item_1(
  id bigint,
  order_no varchar(30),
  user_id bigint,
  price decimal(10,2),
  `count` int,
  primary key(id)
);

create table t_dict(
   id bigint,
   dict_type varchar(200),
   primary key(id)
);