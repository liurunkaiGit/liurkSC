DROP TABLE IF EXISTS `sc_user` ;

CREATE TABLE `sc_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(30) NOT NULL COMMENT '用户名',
  `name` varchar(30) NOT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `balance` decimal DEFAULT NULL COMMENT '余额',
  `create_time` timestamp comment '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `sc_cron`;

CREATE TABLE `sc_cron`  (
  `cron_id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `cron` varchar(30) NOT NULL,
  `type` bigint NOT NULL comment '任务类型'
);