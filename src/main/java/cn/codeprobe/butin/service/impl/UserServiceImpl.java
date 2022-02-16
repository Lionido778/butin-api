package cn.codeprobe.butin.service.impl;

import cn.codeprobe.butin.common.exception.ButinException;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.common.utils.JwtUtil;
import cn.codeprobe.butin.model.dto.UserDTO;
import cn.codeprobe.butin.model.po.User;
import cn.codeprobe.butin.model.vo.LoginVO;
import cn.codeprobe.butin.repository.UserDao;
import cn.codeprobe.butin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private JwtUtil jwtUtil;


    @Cacheable(value = "test", key = "#userId")
    @Override
    public User getUserById(long userId) {
        User user = userDao.getUserById(userId);
        log.info("加载用户信息==>{}", user);
        return user;
    }

    @CacheEvict(value = "test", key = "#userId")
    @Override
    public int updateUserNickname(long userId, String nickname) {
        int effect = userDao.updateUserById(userId, nickname);
        if (effect > 0) {
            log.info("更新用户信息成功");
        } else
            log.info("更新失败");
        return effect;
    }

    @Override
    public UserDTO login(LoginVO loginVO) {
        User user = userDao.getUserByUsername(loginVO.getNickname());
        if (user == null || !user.getPassword().equals(loginVO.getPassword())) {
            throw new ButinException(Status.LOGIN_FAILURE);
        }
        return new UserDTO(user.getId(), user.getNickname(), user.getMobile(), user.getRole());
    }


}
