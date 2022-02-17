package cn.codeprobe.butin.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * bt_article_body
 * @author 
 */
@ApiModel
@Data
public class ArticleBody implements Serializable {
    private Long id;

    /**
     * 所属文章
     */
    @ApiModelProperty(value="所属文章")
    private Long articleId;

    /**
     * 内容(富文本)
     */
    @ApiModelProperty(value="内容(富文本)")
    private String content;

    /**
     * 内容(markdown)
     */
    @ApiModelProperty(value="内容(markdown)")
    private String contentHtml;

    private static final long serialVersionUID = 1L;
}