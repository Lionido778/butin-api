package cn.codeprobe.butin.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * bt_article_body
 *
 * @author
 */
@ApiModel
@Data
public class ArticleBody implements Serializable {
    private Long id;

    /**
     * 内容(富文本)
     */
    @ApiModelProperty(value = "内容(富文本)")
    private String content;

    /**
     * 内容(markdown)
     */
    @ApiModelProperty(value = "内容(markdown)")
    private String contentHtml;

    /**
     * 是否删除（0删除，1未删除）
     */
    @ApiModelProperty(value = "文章状态")
    private Boolean deleted;

    private static final long serialVersionUID = 1L;
}