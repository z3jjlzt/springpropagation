create database if not exists db2;
use db1;
create table if not exists `user1`(
    `id` integer unsigned not null auto_increment,
    `name` varchar(26) not null default '',
    primary key(`id`)
) engine = innodb;

# use db2;
create table if not exists `user2`(
    `id` integer unsigned not null auto_increment,
    `name` varchar(26) not null default '',
    primary key(`id`)
) engine = innodb;
delete from user1;
delete from user2;
