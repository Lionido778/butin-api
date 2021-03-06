package cn.codeprobe.butin.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * bt_article
 *
 * @author
 */
@ApiModel
@Data
public class ArticleDTO implements Serializable {
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 内容id
     */
    @ApiModelProperty(value = "内容id")
    private Long bodyId;

    /**
     * 作者id
     */
    @ApiModelProperty(value = "作者id")
    private Long authorId;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String summary;

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

    /**
     * 是否置顶
     */
    @ApiModelProperty(value = "是否置顶")
    private Boolean weight;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    private static final long serialVersionUID = 1L;
}