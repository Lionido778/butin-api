package cn.codeprobe.butin.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * bt_article_category
 *
 * @author
 */
@ApiModel
@Data
public class ArticleCategory implements Serializable {
    private Long id;

    /**
     * 文章
     */
    @ApiModelProperty(value = "文章")
    private Long articleId;

    /**
     * 目录
     */
    @ApiModelProperty(value = "目录")
    private Long categoryId;

    /**
     * 是否删除（0删除，1未删除）
     */
    @ApiModelProperty(value = "文章状态")
    private Boolean deleted;

    private static final long serialVersionUID = 1L;
}