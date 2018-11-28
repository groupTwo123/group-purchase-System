CREATE TABLE `tb_role` (
  `id` varchar(32) NOT NULL COMMENT '用户id',
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `role` tinyint(2) DEFAULT NULL COMMENT '用户权限： 1:未登录  2:已登录  3:未审核  4:已审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
