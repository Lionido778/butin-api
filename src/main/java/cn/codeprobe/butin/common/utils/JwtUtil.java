package cn.codeprobe.butin.common.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {

    @Value("${butin.jwt.tokenPrefix}")
    private String tokenPrefix;

    @Value("${butin.jwt.secret}")
    private String secret;

    @Value("${butin.jwt.expireTime}")
    private int expireTime;


    //实体（通常指的用户）的状态和额外的元数据
    private final String CLAIMS = "auth";

    /**
     * 生成 token     {"Bearer " + token}
     */
    public String createToken(HashMap<String, Object> map) {
        //签名算法
        Algorithm hmac256 = Algorithm.HMAC256(secret);
        //过期时间
        DateTime expireTime = DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, this.expireTime);
        //claims
        String token = JWT.create()
                .withClaim(CLAIMS, map)
                .withExpiresAt(expireTime)
                //签名算法
                .sign(hmac256);
        log.debug("token: " + tokenPrefix + token);
        return tokenPrefix + token;
    }


    /**
     * 解析 token
     */
    public Map<String, Object> decodeToken(String token) {
        return JWT.decode(token.replace(tokenPrefix, ""))
                .getClaim(CLAIMS)
                .asMap();

    }

    /**
     * 校验 token
     */
    public void verifyToken(String token) throws SignatureVerificationException, TokenExpiredException {
        JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token.replace(tokenPrefix, ""));
    }


}


