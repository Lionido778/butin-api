package cn.codeprobe.butin.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by Lionido on 19/2/2022
 */
@Data
public class CommentPublishVO {

    /**
     * 所属文章
     */
    @ApiModelProperty(value = "所属文章")
    @NotBlank(message = "文章id不可以为空")
    private Long articleId;


    /**
     * 内容
     */
    @NotBlank(message = "评论内容不可以为空")
    @ApiModelProperty(value = "内容")
    private String content;


    /**
     * 要回复的评论id
     */
    @ApiModelProperty(value = "父级评论id")
    private Long parentId;

    /**
     * 要回复的用户id
     */
    @ApiModelProperty(value = "父级评论人")
    private Long toUserId;
}
