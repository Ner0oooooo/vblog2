package com.jzj.vblog.web.controller.admin;

import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.CommentReport;
import com.jzj.vblog.web.service.CommentReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评论举报后台控制器
 * </p>
 *
 * @author Jzj
 * @since 2024-05-20
 */
@Api(tags = "评论举报后台控制器")
@RestController
@RequestMapping("/comment/report")
public class CommentReportController extends BaseController {

    @Autowired
    private CommentReportService commentReportService;

    @ApiOperation("分页列表")
    @GetMapping
    @PreAuthorize("hasAuthority('btn.comment.report.list')")
    public R list(CommentReport commentReport) {
        List<CommentReport> list = commentReportService.list();
        return R.ok(list);
    }

    @ApiOperation("根据id获取评论举报详情")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('btn.comment.report.list')")
    public R getInfo(@PathVariable String id) {
        return R.ok(commentReportService.selectCommentReportById(id));
    }

    @ApiOperation("处理评论举报")
    @PutMapping
    @PreAuthorize("hasAuthority('btn.comment.report.handle')")
    public R handle(@RequestBody CommentReport commentReport) {
        return toAjax(commentReportService.handleCommentReport(commentReport));
    }

    @ApiOperation("删除评论举报")
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('btn.comment.report.del')")
    public R remove(@PathVariable String[] ids) {
        return toAjax(commentReportService.deleteCommentReportByIds(ids));
    }

}