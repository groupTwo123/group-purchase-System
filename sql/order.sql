CREATE TABLE `tb_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `commodity_id` varchar(32) DEFAULT NULL COMMENT '商品id',
  `commodity_number` bigint(20) DEFAULT NULL COMMENT '商品数量',
  `money` double(10,2) DEFAULT NULL COMMENT '商品金额',
  `state` tinyint(2) DEFAULT NULL COMMENT '商品状态',
  `rebate_price` double(10,2) DEFAULT NULL COMMENT '返利金额',
  PRIMARY KEY (`id`),
  KEY `index_name` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
