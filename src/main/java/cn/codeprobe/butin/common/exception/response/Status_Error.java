package cn.codeprobe.butin.common.exception.response;

/**
 * Created by Lionido on 12/2/2022
 * <p>
 * 定义枚举异常状态
 */
public enum Status_Error {
    TEST(4000, "test msg"),
    COMMON(8888, "服务器内部错误，请联系管理员"),
    VALIDATION(5000, "自定义"),
    /* token */
    NULL_TOKEN(5001, "token 是空的"),
    EXPIRE_TOKEN(5002, "token 过期"),
    INVALID_TOKEN(5003, "token 无效"),
    UNAUTHORIZED(40001, "没有权限"),
    NOT_FOUND(40004, "页面丢失"),
    METHOD_NOT_ALLOWED(4005, "METHOD_NOT_ALLOWED"),
    /* login */
    LOGIN_FAILURE(50004, "用户名或密码错误");

    private int errorCode;
    private String errorMsg;

    Status_Error(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Status_Error setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}
