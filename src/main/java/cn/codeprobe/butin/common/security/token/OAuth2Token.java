package cn.codeprobe.butin.common.security.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 封装令牌 token ==> 为认证对象 AuthenticationToken ==> OAuth2Token
 */
public class OAuth2Token implements AuthenticationToken {

    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public String getCredentials() {
        return token;
    }
}
