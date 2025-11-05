-- 修复登录问题的 SQL 脚本

-- 使用数据库
USE `vblog`;

-- 添加 is_super 字段（如果不存在）
ALTER TABLE `sys_user` 
ADD COLUMN `is_super` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否是超级管理员（0:否 1:是）' 
AFTER `is_deleted`;

-- 将 admin 用户设置为超级管理员并修复密码
UPDATE `sys_user` SET 
    `is_super` = '1',
    `password` = '$2a$10$AhJtTxLkpEnhpmNuSzb7ZubX1brN5J5Zmdh.aAh0rT62Tp9yjKjvG'
WHERE `username` = 'admin';

-- 确保其他用户的 is_super 字段为 '0'
UPDATE `sys_user` SET `is_super` = '0' WHERE `is_super` IS NULL AND `username` != 'admin';