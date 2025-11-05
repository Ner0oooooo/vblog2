-- 修复密码字段长度问题

-- 使用数据库
USE `vblog`;

-- 1. 修改 password 字段长度以支持 BCrypt（从 32 增加到 60）
ALTER TABLE `sys_user` MODIFY COLUMN `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码';

-- 2. 添加 is_super 字段（如果不存在）
ALTER TABLE `sys_user` ADD COLUMN `is_super` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否是超级管理员（0:否 1:是）' AFTER `is_deleted`;

-- 3. 修复 admin 用户的密码和权限
UPDATE `sys_user` SET 
    `password` = '$2a$10$AhJtTxLkpEnhpmNuSzb7ZubX1brN5J5Zmdh.aAh0rT62Tp9yjKjvG',  -- BCrypt 编码的 "111111"
    `is_super` = '1',
    `status` = '1'
WHERE `username` = 'admin';

-- 4. 验证修复结果
SELECT 
    username,
    CHAR_LENGTH(password) as password_length,
    CASE 
        WHEN password LIKE '$2a$%' THEN 'BCrypt (正确)'
        ELSE 'MD5 (需要修复)'
    END as password_type,
    is_super,
    status
FROM `sys_user` 
WHERE `username` = 'admin';

SELECT '修复完成！现在可以使用 admin/111111 登录' as result;