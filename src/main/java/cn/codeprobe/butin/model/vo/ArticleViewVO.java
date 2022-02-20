package cn.codeprobe.butin.model.vo;

import cn.codeprobe.butin.model.dto.UserDTO;
import cn.codeprobe.butin.model.po.ArticleBody;
import cn.codeprobe.butin.model.po.Category;
import cn.codeprobe.butin.model.po.Tag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * bt_article
 *
 * @author
 */
@ApiModel(value = "cn.codeprobe.butin.model.po.BtArticle")
@Data
public class ArticleViewVO implements Serializable {
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 作者id
     */
    @ApiModelProperty(value = "作者id")
    private UserDTO user;

    /**
     * body
     */
    @ApiModelProperty(value = "作者id")
    private ArticleBody body;

    /**
     * 标签id
     */
    @ApiModelProperty(value = "标签")
    private List<Tag> tags;

    /**
     * 分类
     */
    @ApiModelProperty(value = "标签")
    private List<Category> categories;

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