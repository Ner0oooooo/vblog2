package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 评论举报VO
 * </p>
 *
 * @author Jzj
 * @since 2024-05-20
 */
@Data
@ApiModel(value="CommentReportVo对象", description="评论举报VO")
public class CommentReportVo {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "评论id")
    private String commentId;

    @ApiModelProperty(value = "评论内容")
    private String commentContent;

    @ApiModelProperty(value = "举报用户id")
    private String userId;

    @ApiModelProperty(value = "举报用户昵称")
    private String userNickname;

    @ApiModelProperty(value = "举报原因")
    private String reason;

    @ApiModelProperty(value = "处理状态(0-未处理,1-已处理)")
    private String status;

    @ApiModelProperty(value = "处理结果(0-删除评论,1-保持原样)")
    private String result;

    @ApiModelProperty(value = "处理人id")
    private String handleUserId;

    @ApiModelProperty(value = "处理人昵称")
    private String handleUserNickname;

    @ApiModelProperty(value = "处理时间")
    private Date handleTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}