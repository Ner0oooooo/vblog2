package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.CommentReport;
import com.jzj.vblog.web.pojo.vo.CommentReportVo;

import java.util.List;

/**
 * <p>
 * 评论举报 服务类
 * </p>
 *
 * @author Jzj
 * @since 2024-05-20
 */
public interface CommentReportService extends IService<CommentReport> {

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

    /**
     * 新增评论举报
     *
     * @param commentReport 评论举报
     * @return 结果
     */
    int insertCommentReport(CommentReport commentReport);

    /**
     * 处理评论举报
     *
     * @param commentReport 评论举报
     * @return 结果
     */
    int handleCommentReport(CommentReport commentReport);

    /**
     * 批量删除评论举报
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCommentReportByIds(String[] ids);

}