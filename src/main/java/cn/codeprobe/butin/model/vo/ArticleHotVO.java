package cn.codeprobe.butin.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * bt_article
 *
 * @author
 */
@ApiModel(value = "cn.codeprobe.butin.model.po.BtArticle")
@Data
public class ArticleHotVO implements Serializable {
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 评论数量
     */
    @ApiModelProperty(value = "评论数量")
    private Integer commentCounts;

    /**
     * 浏览数量
     */
    @ApiModelProperty(value = "浏览数量")
    private Integer viewCounts;


    private static final long serialVersionUID = 1L;
}