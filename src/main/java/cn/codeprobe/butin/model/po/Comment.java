package cn.codeprobe.butin.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * bt_comment
 *
 * @author
 */
@ApiModel
@Data
public class Comment implements Serializable {
    private Long id;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String content;

    /**
     * 所属文章
     */
    @ApiModelProperty(value = "所属文章")
    private Long articleId;

    /**
     * 评论人
     */
    @ApiModelProperty(value = "评论人")
    private Long authorId;

    /**
     * 要回复的评论id
     */
    @ApiModelProperty(value = "父级评论id")
    private Long parentId;

    /**
     * 要回复的用户id
     */
    private Long toUid;

    private int level;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createDate;

    private static final long serialVersionUID = 1L;
}