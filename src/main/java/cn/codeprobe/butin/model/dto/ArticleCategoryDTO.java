package cn.codeprobe.butin.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * bt_article_category
 * @author 
 */
@ApiModel(value="cn.codeprobe.butin.model.po.BtArticleCategory")
@Data
public class ArticleCategoryDTO implements Serializable {
    private Long id;

    /**
     * 文章
     */
    @ApiModelProperty(value="文章")
    private Long articleId;

    /**
     * 目录
     */
    @ApiModelProperty(value="目录")
    private Long categoryId;

    private static final long serialVersionUID = 1L;
}