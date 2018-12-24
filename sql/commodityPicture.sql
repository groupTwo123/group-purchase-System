use group_purchase;
CREATE TABLE `tb_commodity_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `picId` varchar(32) DEFAULT NULL COMMENT '图片id',
  `picBase64` mediumtext COMMENT '图片base64编码',
  `picType` tinyint(2) DEFAULT NULL COMMENT '图片类型  1:用户头像  2:商品图片 3:商品描述图片',
  `priority` int(4) DEFAULT NULL COMMENT '图片优先级存储',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;