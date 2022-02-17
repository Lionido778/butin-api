package cn.codeprobe.butin.model.dto;

import cn.codeprobe.butin.model.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * bt_comment
 * @author 
 */
@ApiModel(value="cn.codeprobe.butin.model.po.BtComment")
@Data
public class CommentDTO implements Serializable {
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
    private User user;

    private Long parentId;

    private User toUser;

    private Boolean level;

    /**
     * 创建日期
     */
    @ApiModelProperty(value="创建日期")
    private Date createDate;

    private static final long serialVersionUID = 1L;
}