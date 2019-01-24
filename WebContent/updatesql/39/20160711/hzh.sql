alter table re_goodsorder add ewmImg VARCHAR(255) COMMENT '二维码图片路径';
#系统参数表
CREATE TABLE  system_config(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	parameter varchar(255)	COMMENT '参数名称',
	verson 	int(11)	COMMENT '版本号'
);