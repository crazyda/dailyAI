
#新用户收货地址表
CREATE TABLE  re_user_address_config(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	user_id  INT(11) 	COMMENT '粉丝Id',
	createTime DATETIME	COMMENT '创建时间',
	name  varchar(255)  COMMENT '收货人',
	phone  varchar(255)  COMMENT '收货人电话',
	province  varchar(20)  COMMENT '省',
	city  varchar(20)  COMMENT '市',
	district  varchar(20)  COMMENT '区县',
	detailedAddress  varchar(255)  COMMENT '详细地址',
	isDefaul bit(1) COMMENT '是否默认值',
	isValid	 bit(1)
);