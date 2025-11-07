package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.utils.SecurityUtils;
import com.jzj.vblog.web.mapper.CommentReportMapper;
import com.jzj.vblog.web.pojo.entity.ArticleComment;
import com.jzj.vblog.web.pojo.entity.CommentReport;
import com.jzj.vblog.web.pojo.vo.CommentReportVo;
import com.jzj.vblog.web.service.ArticleCommentService;
import com.jzj.vblog.web.service.CommentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 评论举报 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2024-05-20
 */
@Service
public class CommentReportServiceImpl extends ServiceImpl<CommentReportMapper, CommentReport> implements CommentReportService {

    @Autowired
    private CommentReportMapper commentReportMapper;

    @Autowired
    private ArticleCommentService articleCommentService;

    @Override
    public List<CommentReportVo> selectCommentReportList(CommentReport commentReport) {
        return commentReportMapper.selectCommentReportList(commentReport);
    }

    @Override
    public CommentReportVo selectCommentReportById(String id) {
        return commentReportMapper.selectCommentReportById(id);
    }

    @Override
    public int insertCommentReport(CommentReport commentReport) {
        // 设置默认状态为未处理
        commentReport.setStatus("0");
        return commentReportMapper.insert(commentReport);
    }

    @Override
    public int handleCommentReport(CommentReport commentReport) {
        // 设置处理人ID和处理时间
        commentReport.setHandleUserId(SecurityUtils.getUserId());
        commentReport.setHandleTime(new Date().toString());
        // 设置处理状态为已处理
        commentReport.setStatus("1");
        // 如果处理结果是删除评论
        if ("0".equals(commentReport.getResult())) {
            // 删除对应的评论
            articleCommentService.removeById(commentReport.getCommentId());
        }
        return commentReportMapper.updateById(commentReport);
    }

    @Override
    public int deleteCommentReportByIds(String[] ids) {
        return commentReportMapper.deleteBatchIds(List.of(ids));
    }

}