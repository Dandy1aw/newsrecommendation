CREATE TABLE `news`
(
    `id`      int(5)      not NULL auto_increment COMMENT '主键',
    `title`   VARCHAR(20) not NULL COMMENT '新闻标题',
    `author`  VARCHAR(10) not NULL COMMENT '作者',
    `url`     VARCHAR(30) not NULL COMMENT '新闻url地址',
    `content` text        NOT NULL COMMENT '新闻内容',
    `pubdate` datetime DEFAULT NULL COMMENT '发布时间',
    `type`    VARCHAR(5) COMMENT '类型',
    PRIMARY KEY (id)
) ENGINE = INNODB
  auto_increment = 1
  DEFAULT CHARSET = utf8;


CREATE TABLE `user`
(
    `id`              INT(5)   NOT NULL COMMENT '用户id',
    `username`        VARCHAR(255) NOT NULL COMMENT '用户名',
    `password`        VARCHAR(32)  DEFAULT NULL COMMENT 'MD5',
    `register_date`   DATETIME     DEFAULT NULL COMMENT '注册时间',
    `last_login_time` DATETIME     DEFAULT NULL COMMENT '上次登录时间',
    PRIMARY KEY (`id`)
) ENGINE = innodb
  DEFAULT CHARSET = utf8mb4;