#意见反馈表
CREATE TABLE  re_feedback(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	isValid	 BIT(1),
	createTime 	DATETIME	COMMENT '创建时间',
	content 	text	COMMENT '意见反馈内容', 
	images	 	text	COMMENT '图片', 
	user_id  INT(11) 	COMMENT '粉丝Id'
);