package cn.codeprobe.butin.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * bt_user
 * @author 
 */
@ApiModel
@Data
public class User implements Serializable {
    private Long id;

    /**
     * 账号
     */
    @ApiModelProperty(value="账号")
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;

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
     * 是否删除
     */
    @ApiModelProperty(value="是否删除")
    private Boolean deleted;

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

    /**
     * 加密盐
     */
    @ApiModelProperty(value="加密盐")
    private String salt;

    /**
     * 注册时间
     */
    @ApiModelProperty(value="注册时间")
    private Date createDate;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value="最后登录时间")
    private Date lastLogin;

    private static final long serialVersionUID = 1L;
}