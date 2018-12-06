CREATE TABLE `tb_admin` (
  `admin_id` varchar(32) NOT NULL COMMENT '管理员id',
  `admin_name` varchar(25) DEFAULT NULL COMMENT '管理员名称',
  `admin_password` varchar(32) DEFAULT NULL COMMENT '管理员密码',
  `admin_head` varchar(255) DEFAULT NULL COMMENT '管理员头像',
  PRIMARY KEY (`admin_id`),
  KEY `index_name` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;