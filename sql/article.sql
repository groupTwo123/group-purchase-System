use group_purchase;
CREATE TABLE `tb_article` (
  `id` varchar(32) NOT NULL COMMENT '用户/商家id',
  `commodity_id` varchar(32) DEFAULT NULL COMMENT '商品id',
  `article` varchar(2000) DEFAULT NULL COMMENT '文章',
  `type` tinyint(4) DEFAULT NULL COMMENT '文章类型  1:评论  2:促销 3:公告 4:会员专享  5:积分商城',
  `state` tinyint(2) DEFAULT '0' COMMENT '状态 0:未启用  1:启用',
  PRIMARY KEY (`id`),
  KEY `idx_id_commodityId` (`id`,`commodity_id`) COMMENT '复合索引，根据用户/商家id和商品id为索引',
  KEY `commodity_id` (`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;