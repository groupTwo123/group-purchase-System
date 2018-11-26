CREATE TABLE `tb_commodity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(20) DEFAULT NULL COMMENT '商品名称',
  `pink` bigint(20) DEFAULT NULL COMMENT '商品点赞率/热度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;