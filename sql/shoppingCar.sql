use group_purchase;
CREATE TABLE `tb_shopping_car` (
  `id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `commodity_id` varchar(50) NOT NULL COMMENT '商品id',
  `commodity_number` bigint(20) DEFAULT NULL COMMENT '购物车中商品的数量',
  `volume_id` varchar(32) DEFAULT NULL COMMENT '仓库id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
