package cn.codeprobe.butin.exception.response;

/**
 * Created by Lionido on 12/2/2022
 * <p>
 * 定义枚举异常状态
 */
public enum Status_Error {
    TEST(4000, "test msg"),
    COMMON(8888, "服务器内部错误，请联系管理员"),
    VALIDATION(5000, "自定义"),
    NOT_FOUND(40004, "页面丢失"),
    METHOD_NOT_ALLOWED(4005, "METHOD_NOT_ALLOWED");

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
