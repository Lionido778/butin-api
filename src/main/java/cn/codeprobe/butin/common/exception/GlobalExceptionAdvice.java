package cn.codeprobe.butin.common.exception;

import cn.codeprobe.butin.common.exception.response.R_Error;
import cn.codeprobe.butin.common.exception.response.Status_Error;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

/**
 * 使用 @RestControllerAdvice 可以实现对所有控制器异常的集中处理
 * 但是处理不了过滤器里的异常（jwt异常） @RestControllerAdvice不能捕获过滤器抛出的异常，对于此类异常需要特别处理
 * <p>
 * 异常日志 已经被记录在 LogAspect
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 捕获404异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public R_Error handleButinException(NoHandlerFoundException e) {
        return new R_Error(Status_Error.NOT_FOUND);
    }

    /**
     * 请求方式不支持
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R_Error handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return new R_Error(Status_Error.METHOD_NOT_ALLOWED);
    }

    /**
     * 捕获自定义业务异常
     */
    @ExceptionHandler(ButinException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R_Error handleButinException(ButinException e) {
        return new R_Error(e.getStatusError().getErrorCode(), e.getMessage());
    }

    /**
     * 捕获自定义业务异常
     */
    @ExceptionHandler(ShiroException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R_Error handleShiroException(ShiroException e) {
        return new R_Error(4001, e.getMessage());
    }

    /**
     * 捕获参数校验异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R_Error handleValidationException(MethodArgumentNotValidException e) {
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return new R_Error(Status_Error.VALIDATION.setErrorMsg(message));
    }


    /**
     * 捕获未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public R_Error handleRuntimeException(RuntimeException e) {
        return new R_Error(Status_Error.COMMON);
    }

    /**
     * 捕获默认异常处理，前面未处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R_Error handleDefaultException(Exception e) {
        return new R_Error(Status_Error.COMMON);
    }

}
