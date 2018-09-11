CREATE TABLE `sys_log` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operation` VARCHAR(50) DEFAULT NULL COMMENT '用户操作(请求的URL)',
  `clazz` VARCHAR(200) DEFAULT NULL COMMENT '执行的类',
  `method` VARCHAR(200) DEFAULT NULL COMMENT '执行的方法',
  `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `params` VARCHAR(3000) DEFAULT NULL COMMENT '请求参数',
  `time` BIGINT UNSIGNED DEFAULT NULL COMMENT '执行时长(毫秒)',
  `modify_user` BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '修改人',
  `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间(默认系统生成)',
  `ip_address` VARCHAR(64) DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='日志表';