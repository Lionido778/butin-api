package cn.codeprobe.butin.exception;

import cn.codeprobe.butin.exception.response.R_Error;
import cn.codeprobe.butin.exception.response.Status_Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 使用 @RestControllerAdvice 可以实现对所有控制器异常的集中处理
 * 但是处理不了过滤器里的异常（jwt异常） @RestControllerAdvice不能捕获过滤器抛出的异常，对于此类异常需要特别处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 捕获404异常
     *
     * @param e NoHandlerFoundException
     * @return Status_Error.NOT_FOUND
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public R_Error ButinException(NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        return new R_Error(Status_Error.NOT_FOUND);
    }

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
     * 捕获参数校验异常
     *
     * @param e BindException
     * @return Status_Error.VALIDATION
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R_Error ValidationException(Exception e) {
        String message = null;
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            message = exception.getBindingResult().getFieldError().getDefaultMessage();
            log.error(message, exception);
        }
        return new R_Error(Status_Error.VALIDATION.setErrorMsg(message));
    }


    /**
     * 默认异常处理，前面未处理
     *
     * @param e Exception
     * @return Status_Error.COMMON
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R_Error defaultException(Exception e) {
        log.error(e.getMessage(), e);
        return new R_Error(Status_Error.COMMON);
    }

}
