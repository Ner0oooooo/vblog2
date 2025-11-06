-- 文章版本历史表
CREATE TABLE `article_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '版本ID',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `version_number` varchar(20) NOT NULL COMMENT '版本号',
  `title` varchar(255) NOT NULL COMMENT '文章标题',
  `content` longtext NOT NULL COMMENT '文章内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(50) NOT NULL COMMENT '创建人',
  `version_desc` varchar(255) DEFAULT NULL COMMENT '版本描述',
  PRIMARY KEY (`id`),
  KEY `idx_article_id` (`article_id`),
  KEY `idx_version_number` (`version_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章版本历史表';

-- 为文章表添加版本号字段
ALTER TABLE `article_summary` ADD COLUMN `current_version` varchar(20) DEFAULT 'v1' COMMENT '当前版本号';