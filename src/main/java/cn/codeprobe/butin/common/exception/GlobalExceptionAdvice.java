package cn.codeprobe.butin.common.exception;

import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.response.Status;
import lombok.extern.slf4j.Slf4j;
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
 * 但是处理不了过滤器 filter 里的异常（jwt异常） @RestControllerAdvice不能捕获过滤器抛出的异常，对于此类异常需要特别处理(可以直接通过 response.getWriter 直接响应给前端)
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
    public R handleNotFoundException(NoHandlerFoundException e) {
        return R.error(Status.NOT_FOUND);
    }

    /**
     * 请求方式不支持
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return R.error(Status.METHOD_NOT_ALLOWED);
    }

    /**
     * 捕获自定义业务异常
     */
    @ExceptionHandler(ButinException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleButinException(ButinException e) {
        return R.error(e.getStatus());
    }

    /**
     * 捕获参数校验异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleValidationException(MethodArgumentNotValidException e) {
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return R.error(Status.VALIDATION.setMsg(message));
    }

    /**
     * 捕获未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public R handleRuntimeException(RuntimeException e) {
        return R.error(Status.INTERNAL_ERROR);
    }

    /**
     * 捕获未知异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleException(Exception e) {
        return R.error(Status.INTERNAL_ERROR);
    }

    /**
     * 捕获默认异常处理，前面未处理
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleDefaultException(Throwable e) {
        return R.error(Status.INTERNAL_ERROR);
    }

}
