package cn.codeprobe.butin.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * bt_article_tag
 * @author 
 */
@ApiModel
@Data
public class ArticleTag implements Serializable {
    private Long id;

    /**
     * 文章
     */
    @ApiModelProperty(value="文章")
    private Long articleId;

    /**
     * 标签
     */
    @ApiModelProperty(value="标签")
    private Long tagId;

    private static final long serialVersionUID = 1L;
}