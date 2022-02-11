package cn.codeprobe.butin.controller.response;

/**
 * Created by Lionido on 11/2/2022
 *
 * 定义返回码枚举
 *  code;       //业务状态码
 *  msg;        //业务消息
 *  success;    //响应是否成功
 */
public enum Status {

    /**********ok**********/
    OK(200, "success", true),
    LOGIN(2000, "登录成功", true
    ),

    /**********error**********/
    ERROR(400, "error", false);


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
}