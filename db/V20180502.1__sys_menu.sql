CREATE TABLE `sys_menu` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单表ID',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '菜单名称',
  `icon` VARCHAR(255) DEFAULT NULL COMMENT '菜单图标',
  `url` VARCHAR(255) DEFAULT NULL COMMENT '访问地址',
  `authority` VARCHAR(255) DEFAULT NULL COMMENT '权限',
  `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `sort` SMALLINT(5) DEFAULT NULL COMMENT '排序',
  `parent_id` BIGINT(20) DEFAULT NULL COMMENT '父菜单ID',
  `create_user` BIGINT(20) DEFAULT NULL COMMENT '创建人',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间(默认当前时间)',
  `is_del` TINYINT(1) DEFAULT '0' COMMENT '删除状态(0,未删除1,已删除)',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='菜单表';
