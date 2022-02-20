package cn.codeprobe.butin.common.security.realm;

import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.common.security.token.OAuth2Token;
import cn.codeprobe.butin.common.utils.JwtUtil;
import cn.codeprobe.butin.model.dto.UserDTO;
import cn.codeprobe.butin.model.po.User;
import cn.codeprobe.butin.repository.UserDao;
import cn.hutool.core.bean.BeanUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Lionido on 13/2/2022
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private UserDao userDao;

    /**
     * 所以这个方法必须重写，因为我们是自定义token，shiro 框架是默认 support: UsernamePasswordToken
     * 所以要支持 我们自定义的 token,返回 true就可以
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权 (验证权限时调用)
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // user 这里获取到的user 是 SimpleAuthenticationInfo(user, token, this.getName()) 放入的user
//        testUser testUser = (testUser) principalCollection.getPrimaryPrincipal();
//        String role = testUser.getRole();
//        LinkedHashSet<String> roles = new LinkedHashSet<>();
        return null;
    }

    /**
     * 认证 (登录验证时调用)
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String token = (String) authenticationToken.getPrincipal();
        Map<String, Object> claims = jwtUtil.decodeToken(token);
        Integer userId = (Integer) claims.get("userId");
        UserDTO userDTO = new UserDTO();
        User user = userDao.selectById(Long.parseLong(userId.toString()));
        if (user == null) {
            throw new LockedAccountException(Status.ACCOUNT_LOCKED.getMsg());
        }
        BeanUtil.copyProperties(user, userDTO);
        return new SimpleAuthenticationInfo(userDTO, token, this.getName());
    }
}
