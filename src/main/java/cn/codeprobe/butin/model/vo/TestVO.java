package cn.codeprobe.butin.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by Lionido on 12/2/2022
 */
@ApiModel
@Data
public class TestVO {

    @NotBlank(message = "用户名不可以为空")
    @ApiModelProperty("用户名")
    private String username;
    @NotBlank(message = "密码不可以为空")
    @ApiModelProperty("密码")
    private String password;

}
