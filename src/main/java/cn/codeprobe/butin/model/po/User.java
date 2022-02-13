package cn.codeprobe.butin.model.po;

import lombok.Data;

import java.io.Serializable;

/**
 * user
 */
@Data
public class User implements Serializable {
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
     * 密码hash值
     */
    private String password;

    /**
     * 角色，角色名以逗号分隔
     */
    private String role;

    private static final long serialVersionUID = 1L;
}