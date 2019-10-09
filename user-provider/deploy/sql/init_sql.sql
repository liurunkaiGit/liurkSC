CREATE TABLE
  IF
  NOT EXISTS `user`
(
  `id`                bigint(11)                                              NOT NULL AUTO_INCREMENT COMMENT '用户id，自增',
  `name`         varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '姓名',
  `user_name`        varchar(45)                                            NULL DEFAULT NULL COMMENT '用户名',
  `age`        int(11)                                                 NULL DEFAULT NULL COMMENT '年龄',
  `balance`   varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

insert into user(name,user_name,age,balance) values ('zs','zs',22,'100');