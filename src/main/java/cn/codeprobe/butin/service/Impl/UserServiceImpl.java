package cn.codeprobe.butin.service.Impl;

import cn.codeprobe.butin.pojo.po.User;
import cn.codeprobe.butin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    private User user = new User(1L, "abc1", "13512345678", "123456", "role-user");

    @Cacheable(value = "test", key = "#userId")
    @Override
    public User getUserById(long userId) {
        log.info("加载用户信息");
        return user;
    }

    @CacheEvict(value = "test", key = "#userId")
    @Override
    public User updateUserNickname(long userId, String nickname) {

        user.setNickname(nickname);

        return user;
    }
}
