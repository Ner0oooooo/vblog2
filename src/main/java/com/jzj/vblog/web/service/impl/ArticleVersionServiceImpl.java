package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.web.mapper.ArticleVersionMapper;
import com.jzj.vblog.web.pojo.entity.ArticleVersion;
import com.jzj.vblog.web.service.ArticleVersionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章版本历史表 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2024-01-01
 */
@Service
public class ArticleVersionServiceImpl extends ServiceImpl<ArticleVersionMapper, ArticleVersion> implements ArticleVersionService {

    @Override
    public ArticleVersion createVersion(Long articleId, String title, String content, String createBy, String versionDesc) {
        // 查询最新版本号
        ArticleVersion latestVersion = baseMapper.selectLatestVersion(articleId);
        String newVersionNumber;
        
        if (latestVersion == null) {
            newVersionNumber = "v1";
        } else {
            // 生成新版本号 v2, v3, ...
            String currentVersion = latestVersion.getVersionNumber();
            int versionNum = Integer.parseInt(currentVersion.substring(1)) + 1;
            newVersionNumber = "v" + versionNum;
        }

        // 创建新版本
        ArticleVersion version = new ArticleVersion();
        version.setArticleId(articleId);
        version.setVersionNumber(newVersionNumber);
        version.setTitle(title);
        version.setContent(content);
        version.setCreateBy(createBy);
        version.setVersionDesc(versionDesc);
        
        baseMapper.insert(version);
        
        return version;
    }

    @Override
    public List<ArticleVersion> getVersionsByArticleId(Long articleId) {
        return baseMapper.selectByArticleId(articleId);
    }

    @Override
    public ArticleVersion getVersionById(Long versionId) {
        return baseMapper.selectById(versionId);
    }

    @Override
    public ArticleVersion getVersionByNumber(Long articleId, String versionNumber) {
        return baseMapper.selectByArticleIdAndVersion(articleId, versionNumber);
    }

    @Override
    public ArticleVersion getLatestVersion(Long articleId) {
        return baseMapper.selectLatestVersion(articleId);
    }
}