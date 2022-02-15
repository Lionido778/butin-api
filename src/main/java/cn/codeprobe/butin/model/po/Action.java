package cn.codeprobe.butin.model.po;

import cn.hutool.core.date.DateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * user_action
 *
 * @author
 */
@ApiModel("用户请求记录")
@AllArgsConstructor
@Data
public class Action implements Serializable {
    /**
     * 用户
     */
    @ApiModelProperty(value = "用户")
    private String username;

    /**
     * 用户访问ip
     */
    @ApiModelProperty(value = "用户访问ip")
    private String ip;

    /**
     * 用户请求url
     */
    @ApiModelProperty(value = "用户请求url")
    private String url;

    /**
     * 用户请求url
     */
    @ApiModelProperty(value = "用户请求时间")
    private DateTime time;

    private static final long serialVersionUID = 1L;
}