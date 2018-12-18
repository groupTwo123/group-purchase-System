use group_purchase;
CREATE TABLE `tb_user` (
  `id` varchar(32) NOT NULL DEFAULT '1' COMMENT '自增id',
  `userName` varchar(25) DEFAULT NULL COMMENT '用户名称',
  `gender` varchar(25) DEFAULT NULL COMMENT '性别',
  `birth` varchar(25) DEFAULT NULL COMMENT '生日',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `type` tinyint(2) DEFAULT '1' COMMENT '用户类型 1：会员  ',
  `area` varchar(100) DEFAULT NULL COMMENT '地区',
  `level` int(11) DEFAULT NULL COMMENT '用户等级',
  `vacancy` double(10,2) DEFAULT 0 COMMENT '用户余额',
  `rebate_id` varchar(32) DEFAULT NULL COMMENT '返利id',
  `stage` tinyint(2) DEFAULT NULL COMMENT '用户登录状态  1：未登录  2：已登录',
  PRIMARY KEY (`id`),
  KEY `index_name` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

