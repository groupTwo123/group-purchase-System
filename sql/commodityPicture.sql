use group_purchase;
CREATE TABLE `tb_commodity_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `commodity_id` varchar(32) DEFAULT NULL COMMENT '商品id',
  `url` LONGTEXT DEFAULT NULL COMMENT '图片地址',
  `priority` int(4) DEFAULT NULL COMMENT '图片优先级存储',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;