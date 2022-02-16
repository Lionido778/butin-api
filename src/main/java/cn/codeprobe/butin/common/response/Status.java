package cn.codeprobe.butin.common.response;

/**
 * Created by Lionido on 11/2/2022
 * <p>
 * 定义枚举响应返回码:
 * code;       //业务状态码
 * msg;        //业务消息
 * success;    //响应是否成功
 */
public enum Status {

    /**********ok**********/
    OK(2000, "success", true),
    LOGIN(2000, "登录成功", true),

    /**********error**********/
    ERROR(4000, "error", false),
    NOT_TOKEN(4001, "登陆凭证为空，请重新登陆", false),
    EXPIRE_TOKEN(4002, "登录信息已失效，请重新登陆", false),
    UNAUTHORIZED(4003, "没有权限，禁止访问", false),
    LOGIN_FAILURE(4004, "登陆失败，请稍后重试", false),
    NOT_FOUND(4005, "页面丢失了", false),
    METHOD_NOT_MATCHED(4006, "请求方法不匹配", false);


    private int code;       //业务状态码
    private String msg;     //描述
    private boolean success;//访问状态

    Status(int code, String msg, boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}