-- 检查文章数据
SELECT COUNT(*) as total_articles FROM article_inform;
SELECT COUNT(*) as published_articles FROM article_inform WHERE status = '1';
SELECT id, article_title, status, create_time FROM article_inform LIMIT 5;

-- 检查字典数据
SELECT * FROM sys_dict_data WHERE dict_type = 'sys_article_tag';
SELECT * FROM sys_dict_type WHERE dict_type = 'sys_article_tag';

-- 检查文章内容表
SELECT COUNT(*) FROM article_content;