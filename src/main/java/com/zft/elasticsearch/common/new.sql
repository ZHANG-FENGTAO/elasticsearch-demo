-- we don't know how to generate schema test (class Schema) :(
create table t_book_bar
(
	id int not null
		primary key,
	bar_num varchar(20) not null comment '书吧编号',
	name varchar(50) default '' null comment '书吧名称',
	address varchar(50) default '' null comment '书吧地址',
	lng double(10,6) null comment '经度',
	lat double(10,6) null comment '纬度',
	phone int null comment '联系方式',
	add_date datetime not null,
	modify_date datetime not null,
	is_deleted int(2) default '0' null,
	constraint t_book_bar_id_uindex
		unique (id),
	constraint t_book_bar_bar_num_uindex
		unique (bar_num)
)
comment '书吧'
;

create table t_user
(
	id int auto_increment
		primary key,
	user_num varchar(20) not null comment '用户编号',
	name varchar(50) default '' not null,
	phone int not null,
	nick_name varchar(50) default '' not null comment '昵称',
	lat double(10,6) null comment '纬度',
	lng double(10,6) null comment '经度',
	sex int(2) default '0' not null comment '性别 0 女 1 男',
	add_date datetime not null,
	modify_date datetime null,
	is_deleted int(2) default '0' not null,
	constraint t_user_user_num_uindex
		unique (user_num),
	constraint t_user_nick_name_uindex
		unique (nick_name)
)
comment '用户表'
;
