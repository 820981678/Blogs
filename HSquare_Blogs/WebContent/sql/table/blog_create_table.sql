
-- 博客标签表
create table BTag(
	id int not null,
	tagname VARCHAR(100)
) COMMENT '博客标签表';
alter table BTag add PRIMARY key (id);
alter table BTag change id id int not null auto_increment;

-- 博客实体表
create table Blog(
	id int not null,
	title VARCHAR(100) not null COMMENT '博客标题',
	overview VARCHAR(200) not null COMMENT '博客文章概述',
	btagId int not null COMMENT '文章标签 对应BTag.id',
	btype int not null COMMENT '文章类型, 对应BType枚举',
	
	userId int not null COMMENT '发布人id',
	userName VARCHAR(32) not null COMMENT '发布人的名称',

	createTime datetime not null COMMENT '博客创建时间',
	updateTime datetime COMMENT '最后一次修改时间',
	checkNum int COMMENT '文章点击量',
	state int not null COMMENT '文章状态 对应BlogState枚举'
) COMMENT '博客实体表';
alter table Blog add PRIMARY key (id);
alter table Blog change id id int not null auto_increment;

-- 博客内容表
create table Blog_text(
	id int not null,
	content text not null COMMENT '博客文章内容',
	blogID int not null COMMENT '关联博客实体表id'
) COMMENT	'博客内容表';
alter table blog_text add PRIMARY key (id);
alter table blog_text change id id int not null auto_increment;

-- 用户表
create table blog_user(
	id int not null,
	name VARCHAR(32) not null,
	password VARCHAR(64) not null,
	
	createtime datetime not null COMMENT '创建时间',
	logintime datetime COMMENT '最后登陆时间'
) COMMENT '用户表';
alter table blog_user add PRIMARY KEY (id);
alter table blog_user change id id int not null auto_increment;
