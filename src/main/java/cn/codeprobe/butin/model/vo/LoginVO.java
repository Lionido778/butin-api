package cn.codeprobe.butin.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by Lionido on 14/2/2022
 */
@Data
@ApiModel
public class LoginVO {

    @ApiModelProperty("昵称")
    @NotBlank(message = "昵称不可以为空")
    private String nickname;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不可以为空")
    private String password;

}
