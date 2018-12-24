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


/*
* 订单状态
* 0：未付款
* 1：已付款
* 2：商家已发货
* 3：交易完成
* 4：已评论
* 5：取消订单申请中
* 6：交易关闭
* 7：退货申请中
* 8：取消退货申请中
* 9：已退货
* */