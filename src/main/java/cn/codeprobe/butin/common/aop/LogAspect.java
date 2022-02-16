package cn.codeprobe.butin.common.aop;

import cn.codeprobe.butin.common.utils.JwtUtil;
import cn.codeprobe.butin.model.dto.UserDTO;
import cn.codeprobe.butin.model.po.Action;
import cn.codeprobe.butin.repository.AccessDao;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Lionido on 14/2/2022
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Resource
    private AccessDao accessDao;

    @Resource
    private JwtUtil jwtUtil;

    @Value("${butin.jwt.header}")
    private String header;

    @Pointcut("execution(public * cn.codeprobe.butin.common.exception.GlobalExceptionAdvice.*(..))")
    public void exceptionAspect() {

    }

    @Before("exceptionAspect()")
    public void before(JoinPoint point) {
        log.debug("======== LogExceptionAspect ========");
        Exception e = null;
        Object[] args = point.getArgs();
        for (Object arg : args) {
            if (arg instanceof Exception) {
                e = (Exception) arg;
                break;
            }
        }
        Map<String, String> map = getUrlAndIp();
        log.debug("用户请求的url为：{}，访问ip地址为：{}", map.get("url"), map.get("ip"));
        log.error("发生未知异常==> {}", Objects.requireNonNull(e).getMessage(), e);
    }

    @Pointcut("execution(public * cn.codeprobe.butin.controller.*.*.*(..))")
    public void requestAspect() {

    }

    @Before("requestAspect()")
    public void requestBefore(JoinPoint point) {
        log.debug("======== RequestActionAspect ========");
        Map<String, String> map = getUrlAndIp();
        //将用户请求（user,ip,url,time）记录到数据库
        accessDao.insert(new Action(getAccessUser(), map.get("ip"), map.get("url"), new DateTime()));
        log.debug("用户请求的url为：{}，访问ip地址为：{}", map.get("url"), map.get("ip"));
    }

    private Map<String, String> getUrlAndIp() {
        // 获取请求的 URL 和 IP
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        // 获取请求 URL
        String url = request.getRequestURL().toString();
        // 获取请求 IP
        String ip = request.getRemoteAddr();
        HashMap<String, String> map = new HashMap<>();
        map.put("url", url);
        map.put("ip", ip);
        return map;
    }

    private String getAccessUser() {
        String currentUser = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String token = request.getHeader(this.header);
        if (StrUtil.isBlank(token)) {
            return "unknown";
        }
        Map<String, Object> map = jwtUtil.decodeToken(token);
        Object user = map.get("user");
        UserDTO userDTO = JSONUtil.toBean(JSONUtil.toJsonStr(user), UserDTO.class);
        if (userDTO != null) {
            currentUser = userDTO.getNickname();
        }
        return currentUser;
    }
}
