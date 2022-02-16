package cn.codeprobe.butin.common.exception;

import cn.codeprobe.butin.common.response.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ButinException extends RuntimeException {

    //异常状态
    private Status status;

    public ButinException(Status status) {
        super(status.getMsg());
        this.status = status;
    }
}
