use group_purchase;
CREATE TABLE `tb_seller` (
  `seller_id` varchar(32) NOT NULL COMMENT '商家id',
  `seller_nickname` varchar(32) DEFAULT NULL COMMENT '商家昵称',
  `seller_name` varchar(25) DEFAULT NULL COMMENT '商家真实姓名',
  `seller_password` varchar(32) DEFAULT NULL COMMENT '商家密码',
  `seller_identity_id` varchar(32) DEFAULT NULL COMMENT '商家身份证号码',
  `seller_phone` varchar(32) DEFAULT NULL COMMENT '商家手机号码',
  `seller_email` varchar(32) DEFAULT NULL COMMENT '商家邮箱',
  `store_name` varchar(25) DEFAULT NULL COMMENT '店铺名称',
  `store_area` varchar(100) DEFAULT NULL COMMENT '店铺位置',
  `seller_pink` bigint(20) DEFAULT NULL COMMENT '商家信誉度',
  `volume_id` varchar(32) DEFAULT NULL COMMENT '仓库id',
  `seller_account` double(25,0) DEFAULT 0 COMMENT '商家账户',
  `state` tinyint default 0 comment '商家注册状态，0为审核中，1位已审核' ,
  PRIMARY KEY (`seller_id`),
  KEY `index_name` (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;