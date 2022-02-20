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
public class ArticleArchiveVO implements Serializable {
    private Long id;

    /**
     * 归档年份
     */
    @ApiModelProperty(value = "年份")
    private String year;

    /**
     * 归档月份
     */
    @ApiModelProperty(value = "月份")
    private String month;

    /**
     * 归档月份
     */
    @ApiModelProperty(value = "数量")
    private Integer count;

    private static final long serialVersionUID = 1L;
}