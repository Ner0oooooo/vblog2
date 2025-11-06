package com.jzj.vblog.web.controller.admin;

import com.jzj.vblog.web.pojo.entity.ArticleVersion;
import com.jzj.vblog.web.service.ArticleVersionService;
import com.jzj.vblog.web.utils.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文章版本历史表 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2024-01-01
 */
@RestController
@RequestMapping("/admin/article/version")
public class ArticleVersionController {

    @Autowired
    private ArticleVersionService articleVersionService;

    /**
     * 根据文章ID查询版本列表
     * @param articleId 文章ID
     * @return 版本列表
     */
    @GetMapping("/list/{articleId}")
    public R<List<ArticleVersion>> getVersionsByArticleId(@PathVariable Long articleId) {
        List<ArticleVersion> versions = articleVersionService.getVersionsByArticleId(articleId);
        return R.ok(versions);
    }

    /**
     * 根据版本ID查询版本详情
     * @param versionId 版本ID
     * @return 版本详情
     */
    @GetMapping("/{versionId}")
    public R<ArticleVersion> getVersionById(@PathVariable Long versionId) {
        ArticleVersion version = articleVersionService.getVersionById(versionId);
        return R.ok(version);
    }

    /**
     * 查询最新版本
     * @param articleId 文章ID
     * @return 最新版本
     */
    @GetMapping("/latest/{articleId}")
    public R<ArticleVersion> getLatestVersion(@PathVariable Long articleId) {
        ArticleVersion version = articleVersionService.getLatestVersion(articleId);
        return R.ok(version);
    }

}