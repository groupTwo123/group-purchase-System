CREATE TABLE `tb_commodity_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `commodity_id` varchar(32) DEFAULT NULL COMMENT '商品id',
  `url` varchar(100) DEFAULT NULL COMMENT '图片地址',
  `local_url` varchar(100) DEFAULT NULL COMMENT '图片所存的本地地址',
  `priority` int(4) DEFAULT NULL COMMENT '图片优先级存储',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;