CREATE TABLE `tb_volume_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `volume_id` varchar(32) DEFAULT NULL COMMENT '仓库id',
  `commodity_id` varchar(32) DEFAULT NULL COMMENT '商品id',
  `commodity_name` varchar(32) DEFAULT NULL COMMENT '商品名称',
  `commodity_number` bigint(20) DEFAULT NULL COMMENT '商品数量',
  `commodity_description` varchar(200) DEFAULT NULL COMMENT '商品描述',
  `commodity_price` double(10,2) DEFAULT NULL COMMENT '商品单价',
  `commodity_type_id` varchar(32) DEFAULT NULL COMMENT '商品类型id',
  PRIMARY KEY (`id`),
  KEY `index_name` (`volume_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
