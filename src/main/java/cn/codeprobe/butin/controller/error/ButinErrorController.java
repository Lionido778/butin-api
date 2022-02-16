package cn.codeprobe.butin.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Spring Boot 对于404 默认是不抛出异常的，都是将错误转发到 /error,由默认的 basicErrorController来处理，所以 RestControllerAdvice是捕获不到404异常
 * 我们可以自定义错误处理类 ButinErrorController，通过实现 ErrorController 来处理一些异常信息
 */

@RestController
public class ButinErrorController implements ErrorController {

    @RequestMapping("/error")
    public void disPatchError(HttpServletRequest req, HttpServletResponse resp) throws NoHandlerFoundException, HttpRequestMethodNotSupportedException {
        //获取转发到 /error 请求里的异常状态代码
        Object status_code = req.getAttribute("javax.servlet.error.status_code");
        switch (String.valueOf(status_code)) {
            case "404":
                throw new NoHandlerFoundException(req.getMethod(), req.getRequestURI(), null);
            case "405":
                throw new HttpRequestMethodNotSupportedException(req.getMethod());

        }
    }


}