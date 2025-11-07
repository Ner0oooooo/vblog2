package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.CommentReport;
import com.jzj.vblog.web.pojo.vo.CommentReportVo;

import java.util.List;

/**
 * <p>
 * 评论举报 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2024-05-20
 */
public interface CommentReportMapper extends BaseMapper<CommentReport> {

    /**
     * 查询评论举报列表
     *
     * @param commentReport 评论举报
     * @return 评论举报集合
     */
    List<CommentReportVo> selectCommentReportList(CommentReport commentReport);

    /**
     * 查询评论举报详情
     *
     * @param id 评论举报ID
     * @return 评论举报
     */
    CommentReportVo selectCommentReportById(String id);

}