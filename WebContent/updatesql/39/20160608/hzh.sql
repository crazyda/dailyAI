商家主页面

CREATE TABLE  seller_main_page(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
	seller_id  INT(11) 	COMMENT '店铺Id',
	beginTime DATETIME	COMMENT '开始时间',
	endTime   DATETIME	COMMENT '结束时间',
	top_carousel_ad  text  COMMENT '顶部轮播广告',
	seller_view  text  COMMENT '店铺视频',
	banner_ad  text  COMMENT '横幅广告',
	isValid	 bit(1)
);