#drop table admin_user_menus;
#drop table admin_user_message;
alter table commodity_type add column `level` int(11) DEFAULT 1 COMMENT '列表等级';