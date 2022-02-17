package cn.codeprobe.butin.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * bt_comment
 * @author 
 */
@ApiModel
@Data
public class Comment implements Serializable {
    private Long id;

    /**
     * 评论内容
     */
    @ApiModelProperty(value="评论内容")
    private String content;

    /**
     * 所属文章
     */
    @ApiModelProperty(value="所属文章")
    private Integer articleId;

    /**
     * 评论人
     */
    @ApiModelProperty(value="评论人")
    private Long authorId;

    private Long parentId;

    private Long toUid;

    private Boolean level;

    /**
     * 创建日期
     */
    @ApiModelProperty(value="创建日期")
    private Date createDate;

    private static final long serialVersionUID = 1L;
}