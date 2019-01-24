alter table admin_user add status INT(1) COMMENT '状态';
#商家账号表
DROP TABLE IF EXISTS seller_account_number;
CREATE TABLE  seller_account_number(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	name  	varchar(255) 	COMMENT '账号名',
	seller_id int(11) COMMENT '商家ID',
	user_id  int(11) COMMENT '粉丝ID',
	level 	int(1) COMMENT '级别',
	isValid	 bit(1)
);