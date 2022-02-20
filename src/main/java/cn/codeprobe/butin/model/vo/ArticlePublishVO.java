package cn.codeprobe.butin.model.vo;

import cn.codeprobe.butin.model.dto.ArticleBodyDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * bt_article
 *
 * @author
 */
@ApiModel(value = "cn.codeprobe.butin.model.po.BtArticle")
@Data
public class ArticlePublishVO implements Serializable {
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @NotBlank(message = "标题不可以为空")
    private String title;


    /**
     * 标签[]
     */
    @ApiModelProperty(value = "标签id")
    @NotBlank(message = "标签不可以为空")
    private List<Long> tagIds;

    /**
     * 分类[]
     */
    @ApiModelProperty(value = "分类id")
    @NotBlank(message = "分类不可以为空")
    private List<Long> categoryIds;

    /**
     * body
     */
    @ApiModelProperty(value = "内容")
    @NotBlank(message = "内容不可以为空")
    private ArticleBodyDTO body;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    @NotBlank(message = "简介不可以为空")
    private String summary;


    private static final long serialVersionUID = 1L;
}