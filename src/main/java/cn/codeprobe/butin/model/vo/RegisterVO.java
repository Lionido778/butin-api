package cn.codeprobe.butin.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * bt_user
 *
 * @author
 */
@ApiModel(value = "注册")
@Data
public class RegisterVO implements Serializable {

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @NotBlank(message = "昵称不可以为空")
    private String nickname;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不可以为空")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobilePhoneNumber;


    private static final long serialVersionUID = 1L;
}