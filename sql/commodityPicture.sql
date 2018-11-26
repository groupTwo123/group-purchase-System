CREATE TABLE `tb_commodity_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `picture_base64` varchar(2000) DEFAULT NULL COMMENT '图片编码',
  `commodity_id` varchar(32) DEFAULT NULL COMMENT '商品id',
  `priority` int(4) DEFAULT NULL COMMENT '图片优先级存储',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;