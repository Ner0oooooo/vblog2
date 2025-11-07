package com.jzj.vblog.web.controller.front;

import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.CommentReport;
import com.jzj.vblog.web.service.CommentReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 评论举报前台控制器
 * </p>
 *
 * @author Jzj
 * @since 2024-05-20
 */
@Api(tags = "评论举报前台控制器")
@RestController
@RequestMapping("/front/comment/report")
@Slf4j
public class CommentReportController extends BaseController {

    @Autowired
    private CommentReportService commentReportService;

    @ApiOperation("举报评论")
    @PostMapping
    public R reportComment(@RequestBody CommentReport commentReport) {
        return toAjax(commentReportService.insertCommentReport(commentReport));
    }

}