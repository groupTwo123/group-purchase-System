CREATE TABLE `tb_role` (
  `id` varchar(32) NOT NULL COMMENT '用户id',
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `role` tinyint(2) DEFAULT NULL COMMENT '1:游客   2：会员用户  3：管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
