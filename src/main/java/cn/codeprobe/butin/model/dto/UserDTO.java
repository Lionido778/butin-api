package cn.codeprobe.butin.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * bt_user
 * @author 
 */
@ApiModel(value="cn.codeprobe.butin.model.po.BtUser")
@Data
public class UserDTO implements Serializable {
    private Long id;

    /**
     * 账号
     */
    @ApiModelProperty(value="账号")
    private String account;

    /**
     * 昵称
     */
    @ApiModelProperty(value="昵称")
    private String nickname;

    /**
     * 是否管理员
     */
    @ApiModelProperty(value="是否管理员")
    private Boolean admin;

    /**
     * 头像
     */
    @ApiModelProperty(value="头像")
    private String avatar;


    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    private String mobilePhoneNumber;

    /**
     * 状态
     */
    @ApiModelProperty(value="状态")
    private Boolean status;


    private static final long serialVersionUID = 1L;
}