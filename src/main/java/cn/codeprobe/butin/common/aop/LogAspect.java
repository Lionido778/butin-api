package cn.codeprobe.butin.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lionido on 14/2/2022
 */
@Slf4j
@Aspect
@Component
public class LogAspect {


    @Pointcut("execution(public * cn.codeprobe.butin.common.exception.GlobalExceptionAdvice.*(..))")
    public void aspect() {

    }

    @Before("aspect()")
    public void before(JoinPoint point) {
        log.debug("========LogAspect========");
        Exception e = null;
        Object[] args = point.getArgs();
        for (Object arg : args) {
            if (arg instanceof Exception) {
                e = (Exception) arg;
                break;
            }
        }
        //获取请求的 URL 和 IP
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 获取请求 URL
        String url = request.getRequestURL().toString();
        // 获取请求 IP
        String ip = request.getRemoteAddr();
        log.debug("用户请求的url为：{}，访问ip地址为：{}", url, ip);
        assert e != null;
        log.error("发生未知异常{}", e.getMessage(), e);
    }

}
