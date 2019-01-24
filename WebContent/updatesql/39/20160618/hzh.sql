DROP TABLE IF EXISTS re_goodsorder;
DROP TABLE IF EXISTS re_goodsorder_item;
#主订单
CREATE TABLE  re_goodsorder(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	user_id  INT(11) 	COMMENT '粉丝Id',
	createTime DATETIME	COMMENT '创建时间',
	isValid	 BIT(1),
	order_code VARCHAR(255) COMMENT '订单编码',
	pay_price  DOUBLE(11,2)  COMMENT '订单现金',
	pay_score  INT(11)  COMMENT '订单积分',
	pay_cashpoint  DOUBLE(11,2)  COMMENT '订单红包',
	pay_type INT(11)  COMMENT '支付类型',
	pay_account VARCHAR(255) COMMENT '第三方支付账号',
	account_name VARCHAR(255) COMMENT '账号用户名',
	realname VARCHAR(255) COMMENT '收件人姓名',
	address VARCHAR(255) COMMENT '收件人地址',
	phone VARCHAR(255) COMMENT '收件人电话',
	STATUS INT(11) COMMENT '订单状态',
	message TEXT COMMENT '买家留言',
	logistics_compay VARCHAR(255) COMMENT '配送公司',
	logistics_code VARCHAR(255) COMMENT '快递单号',
	logistics VARCHAR(255) COMMENT '配送类型',
	seller_phone VARCHAR(255) COMMENT '商家电话',
	seller_address VARCHAR(255) COMMENT '商家地址',
	logisticsType DOUBLE(11,2) COMMENT '邮费',
	confirmtime DATETIME COMMENT '自动确认时间'
);

#子订单
CREATE TABLE  re_goodsorder_item(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	user_id  INT(11) 	COMMENT '粉丝Id',
	createTime DATETIME	COMMENT '创建时间',
	isValid	 BIT(1),
	order_id INT(11) COMMENT '主订单Id',
	good_id  INT(11)  COMMENT '唯一商品id',
	preferential  text  COMMENT '优惠信息',
	good_name  VARCHAR(255)  COMMENT '商品名',
	good_img  VARCHAR(255)  COMMENT '商品封面',
	goodsStandardIds  VARCHAR(255)  COMMENT '商品规格ID',
	goodsStandardString  VARCHAR(255)  COMMENT '商品规格',
	good_price  DOUBLE(11,2)  COMMENT '商品现金',
	good_score  INT(11)  COMMENT '商品积分',
	good_cashpoint  DOUBLE(11,2)  COMMENT '商品红包',
	good_quantity INT(11) COMMENT '商品数量',
	pay_price  DOUBLE(11,2)  COMMENT '订单现金',
	pay_score  INT(11)  COMMENT '订单积分',
	pay_cashpoint  DOUBLE(11,2)  COMMENT '订单红包',
	mall_id INT(11)  COMMENT '模块id',
	mall_class VARCHAR(255)  COMMENT '模块',
	STATUS INT(11) COMMENT '订单状态',
	isBack INT(11) COMMENT '退单状态',
	logisticsType INT(11) COMMENT '配送方式',
	logisticsPrice DOUBLE(11,2) COMMENT '邮费',
	car_id INT(11) COMMENT '购物车id'
);