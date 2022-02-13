package cn.codeprobe.butin.pojo.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    private long id;
    private String nickname;
    private String mobile;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //在输出的Json数据中隐藏密码，只能输入不输出
    private String password;
    private String role;

    public User(long id, String nickname, String mobile, String password, String role) {
        this.id = id;
        this.nickname = nickname;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
    }

    public User() {
        super();
    }


}
