#主订单
CREATE TABLE  re_goodsorder(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	user_id  INT(11) 	COMMENT '粉丝Id',
	createTime DATETIME	COMMENT '创建时间',
	isValid	 bit(1),
	order_id int(11) COMMENT '订单Id',
	order_code varchar(255) COMMENT '订单编码',
	pay_price  double(11,2)  COMMENT '订单现金',
	pay_score  int(11)  COMMENT '订单积分',
	pay_cashpoint  double(11,2)  COMMENT '订单红包',
	pay_type int(11)  COMMENT '支付类型',
	pay_account varchar(255) COMMENT '第三方支付账号',
	account_name varchar(255) COMMENT '账号用户名',
	realname varchar(255) COMMENT '收件人姓名',
	address varchar(255) COMMENT '收件人地址',
	phone varchar(255) COMMENT '收件人电话',
	status int(11) COMMENT '订单状态',
	message text COMMENT '买家留言',
	logistics_compay varchar(255) COMMENT '配送公司',
	logistics_code varchar(255) COMMENT '快递单号',
	logistics varchar(255) COMMENT '配送类型',
	seller_phone varchar(255) COMMENT '商家电话',
	seller_address varchar(255) COMMENT '商家地址',
	logisticsType double(11，2) COMMENT '邮费',
	confirmtime DATETIME COMMENT '自动确认时间'
);

#子订单
CREATE TABLE  re_goodsorder_item(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	user_id  INT(11) 	COMMENT '粉丝Id',
	createTime DATETIME	COMMENT '创建时间',
	isValid	 bit(1),
	order_id int(11) COMMENT '主订单Id',
	good_id  varchar(255)  COMMENT '唯一商品id',
	good_name  varchar(255)  COMMENT '商品名',
	good_img  varchar(255)  COMMENT '商品封面',
	good_spec  varchar(255)  COMMENT '商品规格',
	good_price  double(11,2)  COMMENT '商品现金',
	good_score  int(11)  COMMENT '商品积分',
	good_cashpoint  double(11,2)  COMMENT '商品红包',
	good_quantity int(11) COMMENT '商品数量',
	pay_price  double(11,2)  COMMENT '订单现金',
	pay_score  int(11)  COMMENT '订单积分',
	pay_cashpoint  double(11,2)  COMMENT '订单红包',
	mall_id int(11)  COMMENT '模块id',
	mall_class varchar(255)  COMMENT '模块',
	status int(11) COMMENT '订单状态',
	isBack int(11) COMMENT '退单状态',
	logisticsType int(11) COMMENT '配送方式',
	logisticsType double(11，2) COMMENT '邮费',
	car_id int(11) COMMENT '购物车id'
);

ALTER TABLE commodity_type ADD COLUMN level int(11) COMMENT '分类等级';