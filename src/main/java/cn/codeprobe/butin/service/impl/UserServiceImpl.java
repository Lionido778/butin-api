package cn.codeprobe.butin.service.impl;

import cn.codeprobe.butin.model.po.User;
import cn.codeprobe.butin.repository.UserDao;
import cn.codeprobe.butin.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Lionido on 16/2/2022
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User findUserById(long userId) {
        return userDao.selectById(userId);
    }
}
