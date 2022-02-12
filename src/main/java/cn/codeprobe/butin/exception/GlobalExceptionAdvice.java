package cn.codeprobe.butin.exception;

import cn.codeprobe.butin.exception.response.R_Error;
import cn.codeprobe.butin.exception.response.Status_Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Lionido on 12/2/2022
 * 全局异常捕获
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 捕获自定义异常
     *
     * @param e ButinException 自定义异常
     * @return R_Error
     */
    @ExceptionHandler(ButinException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R_Error ButinException(ButinException e) {
        log.error(e.getMessage(), e);
        return new R_Error(e.getStatusError().getErrorCode(), e.getMessage());
    }


    /**
     * 捕获运行时异常
     *
     * @param e RuntimeException
     * @return Status_Error.COMMON
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R_Error commonException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new R_Error(Status_Error.COMMON);
    }


}
