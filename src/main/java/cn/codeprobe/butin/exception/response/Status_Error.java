package cn.codeprobe.butin.exception.response;

/**
 * Created by Lionido on 12/2/2022
 * <p>
 * 定义枚举异常状态
 */
public enum Status_Error {
    TEST(4000, "test msg"),
    COMMON(8888, "网站发生错误，请联系管理员");


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
}
