package cn.codeprobe.butin.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * bt_article_version
 *
 * @author
 */
@ApiModel(value = "cn.codeprobe.butin.model.po.BtArticleVersion")
@Data
public class ArticleVersion implements Serializable {
    private Integer id;

    /**
     * 旧版本
     */
    @ApiModelProperty(value = "旧版本")
    private Long oldArticleId;

    /**
     * 新版本
     */
    @ApiModelProperty(value = "新版本")
    private Long newArticleId;

    private static final long serialVersionUID = 1L;
}