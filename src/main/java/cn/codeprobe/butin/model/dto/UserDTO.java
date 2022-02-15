package cn.codeprobe.butin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Lionido on 14/2/2022
 */
@Data
@AllArgsConstructor
public class UserDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 角色，角色名以逗号分隔
     */
    private String role;
}
