package cn.codeprobe.butin.model.vo;

import cn.codeprobe.butin.model.dto.CommentDTO;
import cn.codeprobe.butin.model.dto.UserDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * bt_comment
 *
 * @author
 */
@ApiModel(value = "cn.codeprobe.butin.model.po.BtComment")
@Data
public class CommentListVO implements Serializable {
    private Long id;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String content;

    /**
     * 所属文章
     */
    @ApiModelProperty(value = "所属文章")
    private Long articleId;

    /**
     * 评论人
     */
    @ApiModelProperty(value = "评论人")
    private UserDTO user;

    /**
     * 评论人
     */
    @ApiModelProperty(value = "评论人")
    private Long parentId;

    /**
     * 评论人
     */
    @ApiModelProperty(value = "评论人")
    private List<CommentDTO> childrens;

    /**
     * 被评论人
     */
    @ApiModelProperty(value = "被评论人")
    private UserDTO toUser;

    /**
     * 评论级别
     */
    @ApiModelProperty(value = "评论级别")
    private int level;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    private static final long serialVersionUID = 1L;
}