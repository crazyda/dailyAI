CREATE TABLE  re_black_order(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	BackCode  VARCHAR(255)  COMMENT '退单编码',
	isValid	 BIT(1),
	createTime DATETIME	COMMENT '创建时间',
	user_id  INT(11) 	COMMENT '粉丝Id',
	orderItemId INT(11) COMMENT '订单项id',
	goodid INT(11)  COMMENT '唯一商品id',
	goodQuantity INT(11) COMMENT '退单商品数量',
	sellerCode INT(11) COMMENT '商家编码',
	mall_id INT(11)  COMMENT '模块id',
	mall_class VARCHAR(255)  COMMENT '模块',
	bcakmoney DOUBLE(11,2)  COMMENT '退单金额',	
	paymoney DOUBLE(11,2)  COMMENT '实际支付金额',
	checkMessage  VARCHAR(255)  COMMENT  '审核意见',
	sellerId INT(11) COMMENT '商家id',	
	backtype INT(2)  COMMENT '退款类型',
	backstate INT(2)  COMMENT '退单状态',	
	paytype INT(2)  COMMENT '支付类型',
	payAccout VARCHAR(255)  COMMENT  '支付账号',	
	accountName  VARCHAR(255)  COMMENT  '支付账号真实姓名',	
	reason	VARCHAR(255)  COMMENT  '退单原因',	
	image	VARCHAR(255)  COMMENT  '退单图片',	
	TYPE	INT(2)  COMMENT '退款途径'	
);
alter table re_black_order add pay_score  INT(11)  COMMENT '订单积分';
alter table re_black_order addpay_cashpoint  DOUBLE(11,2)  COMMENT '订单红包';