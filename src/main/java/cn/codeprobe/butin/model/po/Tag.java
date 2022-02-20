package cn.codeprobe.butin.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * bt_tag
 *
 * @author
 */
@ApiModel
@Data
public class Tag implements Serializable {
    private Long id;

    /**
     * 标签图标
     */
    @ApiModelProperty(value = "标签图标")
    private String icon;

    /**
     * 标签名
     */
    @ApiModelProperty(value = "标签名")
    private String name;

    private static final long serialVersionUID = 1L;
}