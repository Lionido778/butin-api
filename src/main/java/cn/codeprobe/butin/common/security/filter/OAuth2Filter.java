package cn.codeprobe.butin.common.security.filter;

import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.common.security.threadlocal.ThreadLocalToken;
import cn.codeprobe.butin.common.security.token.OAuth2Token;
import cn.codeprobe.butin.common.utils.JwtUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 自定义拦截规则
 */
@Slf4j
@Component
@Scope("prototype")
public class OAuth2Filter extends AuthenticatingFilter {

    @Value("${butin.jwt.header}")
    private String tokenInHeader;

    @Value("${butin.jwt.expireTimeCache}")
    private Long expireTimeCache;

    @Resource
    private ThreadLocalToken threadLocalToken;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private JwtUtil jwtUtil;


    /**
     * 从前端请求头中取出 token 封装为 AuthenticationToken 认证令牌
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader(tokenInHeader);
        if (StrUtil.isNotBlank(token)) {
            return new OAuth2Token(token);
        }
        return null;
    }

    /**
     * 判断当前请求是否允许访问
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * 处理被拦截的请求
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //令牌校验
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader(tokenInHeader);
        if (StrUtil.isBlank(token)) {
            //令牌为空 登录失败 响应前端失败信息
            responseJson(servletResponse, JSONUtil.toJsonStr(R.error(Status.NULL_TOKEN)));
            return false;
        }
        try {
            jwtUtil.verifyToken(token);
        } catch (TokenExpiredException e) {
            //令牌刷新
            Object claims = redisTemplate.opsForValue().get(token);
            if (claims == null) {
                responseJson(servletResponse, JSONUtil.toJsonStr(R.error(Status.EXPIRE_TOKEN)));
                return false;
            }
            log.debug("claim ==> map" + BeanUtil.beanToMap(claims));
            String refreshToken = jwtUtil.createToken(BeanUtil.beanToMap(claims));
            threadLocalToken.clear();
            threadLocalToken.saveToken(refreshToken);
            redisTemplate.opsForValue().set(refreshToken, claims, expireTimeCache, TimeUnit.DAYS);
        } catch (Exception e) {
            responseJson(servletResponse, JSONUtil.toJsonStr(R.error(Status.INVALID_TOKEN)));
            return false;
        }
        //执行认证登录
        return executeLogin(servletRequest, servletResponse);
    }

    /**
     * 处理 认证失败
     *
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        String remoteAddr = req.getRemoteAddr();
        log.debug("{} 访问 {} 认证失败", remoteAddr, requestURI);
        //认证失败 响应前端
        responseJson(response, JSONUtil.toJsonStr(R.error(Status.UNAUTHORIZED)));
        return super.onLoginFailure(token, e, request, response);
    }

    /**
     * 处理 认证成功
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        String remoteAddr = req.getRemoteAddr();
        log.debug("{} 访问 {} 认证成功", remoteAddr, requestURI);
        return super.onLoginSuccess(token, subject, request, response);
    }

    private void responseJson(ServletResponse response, String messageJson) {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        resp.setStatus(HttpStatus.UNAUTHORIZED.value());
        //TODO 跨域
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(messageJson);
        } catch (IOException e) {
            log.error("登录错误信息响应失败", e);
        } finally {
            Objects.requireNonNull(writer).close();
        }
    }

}
