package com.jzj.vblog.web.pojo.entity;

import com.jzj.vblog.web.pojo.entity.base.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评论举报
 * </p>
 *
 * @author Jzj
 * @since 2024-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CommentReport对象", description="评论举报")
public class CommentReport extends CommonEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论id")
    private String commentId;

    @ApiModelProperty(value = "举报用户id")
    private String userId;

    @ApiModelProperty(value = "举报原因")
    private String reason;

    @ApiModelProperty(value = "处理状态(0-未处理,1-已处理)")
    private String status;

    @ApiModelProperty(value = "处理结果(0-删除评论,1-保持原样)")
    private String result;

    @ApiModelProperty(value = "处理人id")
    private String handleUserId;

    @ApiModelProperty(value = "处理时间")
    private String handleTime;

}