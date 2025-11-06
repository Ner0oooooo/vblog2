package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.ArticleVersion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文章版本历史表 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2024-01-01
 */
public interface ArticleVersionMapper extends BaseMapper<ArticleVersion> {

    /**
     * 根据文章ID查询版本列表
     * @param articleId 文章ID
     * @return 版本列表
     */
    List<ArticleVersion> selectByArticleId(@Param("articleId") Long articleId);

    /**
     * 根据文章ID和版本号查询版本
     * @param articleId 文章ID
     * @param versionNumber 版本号
     * @return 版本信息
     */
    ArticleVersion selectByArticleIdAndVersion(@Param("articleId") Long articleId, @Param("versionNumber") String versionNumber);

    /**
     * 查询最新版本
     * @param articleId 文章ID
     * @return 最新版本
     */
    ArticleVersion selectLatestVersion(@Param("articleId") Long articleId);

}