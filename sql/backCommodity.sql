CREATE TABLE `tb_back_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `back_order_id` varchar(32) DEFAULT NULL COMMENT '退款单id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `commodity_id` varchar(32) DEFAULT NULL COMMENT '商品id',
  `commodity_number` bigint(20) DEFAULT NULL COMMENT '商品数量',
  `money` double(10,2) DEFAULT NULL COMMENT '商品金额',
  `back_reason` varchar(200) DEFAULT NULL COMMENT '退款理由',
  `state` tinyint(2) DEFAULT NULL COMMENT '退款状态',
  PRIMARY KEY (`id`),
  KEY `index_name` (`back_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
