package cn.codeprobe.butin.exception;

import cn.codeprobe.butin.exception.response.Status_Error;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Lionido on 12/2/2022
 * 自定义异常类
 */
@Data
public class ButinException extends RuntimeException {

    //异常状态
    private Status_Error statusError;

    public ButinException(Status_Error statusError) {
        super(statusError.getErrorMsg());
        this.statusError = statusError;
    }
}
