drop table if exists ad_task;
drop table if exists ad_user;

create table ad_sms_log(
	id bigint auto_increment,
	sms varchar(70),
	mobile varchar(14) not null,
	vtime varchar(20),
	flag int(1),
	primary key (id)
)engine=InnoDB;

create table ad_task (
	id bigint auto_increment,
	title varchar(128) not null,
	description varchar(255),
	user_id bigint not null,
    primary key (id)
) engine=InnoDB;

create table ad_user (
	id bigint auto_increment,
	login_name varchar(64) not null unique,
	name varchar(64) not null,
	password varchar(255) not null,
	salt varchar(64) not null,
	roles varchar(255) not null,
	register_date timestamp not null default 0,
	primary key (id)
) engine=InnoDB;

create table ad_req_log(
	id bigint auto_increment,
	channel varchar(20) not null,
	ipaddr varchar(15) not null,	
	prov varchar(10),
	vtime varchar(20),
	ua varchar(20),
	vstr1 varchar(20),
	primary key (id)
);

create table ad_ord_log(
	id bigint auto_increment,
	uname	varchar(16) not null,
	birthday varchar(8) not null,
	ddlSex varchar(2) not null,
	phone	varchar(14) not null,
	ipaddr varchar(15) not null,
	prov varchar(10),
	vtime varchar(20),
	primary key (id)
);