package cn.codeprobe.butin.common.security.aop;

import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.security.threadlocal.ThreadLocalToken;
import cn.hutool.core.util.StrUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Lionido on 14/2/2022
 */

@Aspect
@Component
public class TokenAspect {

    @Resource
    private ThreadLocalToken threadLocalToken;

    @Value("${butin.jwt.header}")
    private String header_key;

    @Pointcut("execution(public * cn.codeprobe.butin.controller.*.*.*(..))")
    public void aspect() {

    }

    @Around("aspect()")
    public R around(ProceedingJoinPoint point) throws Throwable {
        R r = (R) point.proceed();
        String token = threadLocalToken.getToken();
        if (StrUtil.isNotBlank(token)) {
            r.put(header_key,token);
            threadLocalToken.clear();
        }
        return r;
    }

}
