package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.ArticleVersion;

import java.util.List;

/**
 * <p>
 * 文章版本历史表 服务类
 * </p>
 *
 * @author Jzj
 * @since 2024-01-01
 */
public interface ArticleVersionService extends IService<ArticleVersion> {

    /**
     * 创建文章版本
     * @param articleId 文章ID
     * @param title 文章标题
     * @param content 文章内容
     * @param createBy 创建人
     * @param versionDesc 版本描述
     * @return 版本信息
     */
    ArticleVersion createVersion(Long articleId, String title, String content, String createBy, String versionDesc);

    /**
     * 根据文章ID查询版本列表
     * @param articleId 文章ID
     * @return 版本列表
     */
    List<ArticleVersion> getVersionsByArticleId(Long articleId);

    /**
     * 根据版本ID查询版本
     * @param versionId 版本ID
     * @return 版本信息
     */
    ArticleVersion getVersionById(Long versionId);

    /**
     * 根据文章ID和版本号查询版本
     * @param articleId 文章ID
     * @param versionNumber 版本号
     * @return 版本信息
     */
    ArticleVersion getVersionByNumber(Long articleId, String versionNumber);

    /**
     * 查询最新版本
     * @param articleId 文章ID
     * @return 最新版本
     */
    ArticleVersion getLatestVersion(Long articleId);

    /**
     * 对比两个版本的差异
     * @param versionId1 第一个版本ID
     * @param versionId2 第二个版本ID
     * @return 差异信息
     */
    Map<String, Object> compareVersions(Long versionId1, Long versionId2);

}