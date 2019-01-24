alter table re_goodsOfLocalSpecialtyMall add cashBack DOUBLE(11,2) COMMENT '返现';
alter table re_goodsOfNineNineMall add cashBack DOUBLE(11,2) COMMENT '返现';

alter table re_goodsOfLocalSpecialtyMall add addedTime DATETIME COMMENT '上架时间';
alter table re_goodsOfLocalSpecialtyMall add shelvesTime DATETIME COMMENT '下架时间';
alter table re_goodsOfMemberMall add addedTime DATETIME COMMENT '上架时间';
alter table re_goodsOfMemberMall add shelvesTime DATETIME COMMENT '下架时间';
alter table re_goodsOfNineNineMall add addedTime DATETIME COMMENT '上架时间';
alter table re_goodsOfNineNineMall add shelvesTime DATETIME COMMENT '下架时间';
alter table re_goodsOfScoreMall add addedTime DATETIME COMMENT '上架时间';
alter table re_goodsOfScoreMall add shelvesTime DATETIME COMMENT '下架时间';
alter table re_goodsOfSeckillMall add addedTime DATETIME COMMENT '上架时间';
alter table re_goodsOfSeckillMall add shelvesTime DATETIME COMMENT '下架时间';
alter table re_goodsOfSellerMall add addedTime DATETIME COMMENT '上架时间';
alter table re_goodsOfSellerMall add shelvesTime DATETIME COMMENT '下架时间';


alter table re_goodsOfLocalSpecialtyMall add placeOfProduction VARCHAR(255) COMMENT '所在产地';
alter table re_goodsOfLocalSpecialtyMall add pack VARCHAR(255) COMMENT '包装';
alter table re_goodsOfNineNineMall add placeOfProduction VARCHAR(255) COMMENT '所在产地';
alter table re_goodsOfNineNineMall add pack VARCHAR(255) COMMENT '包装';