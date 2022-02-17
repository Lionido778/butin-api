package cn.codeprobe.butin.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * bt_category
 * @author 
 */
@ApiModel
@Data
public class Category implements Serializable {
    private Long id;

    /**
     * 目录图标
     */
    @ApiModelProperty(value="目录图标")
    private String avatar;

    /**
     * 目录名称
     */
    @ApiModelProperty(value="目录名称")
    private String name;

    /**
     * 目录简介
     */
    @ApiModelProperty(value="目录简介")
    private String description;

    private static final long serialVersionUID = 1L;
}