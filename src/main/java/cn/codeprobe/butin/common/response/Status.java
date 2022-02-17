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
    // common
    OK(2000, "success", true),
    TEST(1000, "测试", true),

    //login
    LOGIN_SUCCESS(2001, "登录成功", true),


    /**********error**********/
    //common
    INTERNAL_ERROR(5000, "服务器内部错误，请联系管理员", false),
    VALIDATION(5001, "自定义", false),
    NOT_FOUND(4004, "页面找不到啦", false),
    METHOD_NOT_ALLOWED(4005, "请求方式错误", false),
    //login
    ERROR(4000, "error", false),
    UNAUTHORIZED(4001, "没有权限", false),
    NULL_TOKEN(4002, "登陆凭证为空，请登陆后尝试", false),
    EXPIRE_TOKEN(4003, "登陆凭证过期，请登陆后尝试", false),
    INVALID_TOKEN(4004, "登陆凭证无效，请登陆后尝试", false),
    LOGIN_FAILURE(4005, "登陆失败，请稍后重试", false),
    ACCOUNT_LOCKED(4006, "账户已被锁定", false),
    //业务
    ARTICLE_LIST_NULL(5003, "文章列表为空", false),
    ARTICLE_LIST_FAILURE(5004, "获取文章列表失败", false);


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

    public Status setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
