package cn.codeprobe.butin.exception.response;

import lombok.Data;

/**
 * Created by Lionido on 12/2/2022
 * 统一异常响应格式
 */
@Data
public class R_Error {

    private int errorCode;
    private String errorMsg;

    public R_Error(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public R_Error(Status_Error status_error) {
        this.errorCode = status_error.getErrorCode();
        this.errorMsg = status_error.getErrorMsg();
    }
}
