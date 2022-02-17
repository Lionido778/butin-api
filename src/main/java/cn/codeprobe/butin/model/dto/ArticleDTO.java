package cn.codeprobe.butin.model.dto;

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
 * @author 
 */
@ApiModel(value="cn.codeprobe.butin.model.po.BtArticle")
@Data
public class ArticleDTO implements Serializable {
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value="标题")
    private String title;

    /**
     * 内容id
     */
    @ApiModelProperty(value="内容id")
    private ArticleBody articleBody;

    /**
     * 作者id
     */
    @ApiModelProperty(value="作者id")
    private UserDTO user;

    /**
     * 类别id
     */
    @ApiModelProperty(value="类别id")
    private List<Category> categories;

    /**
     * 标签id
     */
    @ApiModelProperty(value="标签id")
    private List<Tag> tags;

    /**
     * 简介
     */
    @ApiModelProperty(value="简介")
    private String summary;

    /**
     * 评论数量
     */
    @ApiModelProperty(value="评论数量")
    private Integer commentCounts;

    /**
     * 浏览数量
     */
    @ApiModelProperty(value="浏览数量")
    private Integer viewCounts;

    /**
     * 是否置顶
     */
    @ApiModelProperty(value="是否置顶")
    private Integer weight;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createDate;

    private static final long serialVersionUID = 1L;
}