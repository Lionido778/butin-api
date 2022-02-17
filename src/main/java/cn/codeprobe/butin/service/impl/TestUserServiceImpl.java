package cn.codeprobe.butin.service.impl;

import cn.codeprobe.butin.common.exception.ButinException;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.common.utils.JwtUtil;
import cn.codeprobe.butin.model.dto.testUserDTO;
import cn.codeprobe.butin.model.po.testUser;
import cn.codeprobe.butin.model.vo.LoginVO;
import cn.codeprobe.butin.repository.TestUserDao;
import cn.codeprobe.butin.service.TestUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("testUserService")
public class TestUserServiceImpl implements TestUserService {

    @Resource
    private TestUserDao testUserDao;

    @Resource
    private JwtUtil jwtUtil;


    @Cacheable(value = "test", key = "#userId")
    @Override
    public testUser getUserById(long userId) {
        testUser testUser = testUserDao.getUserById(userId);
        log.info("加载用户信息==>{}", testUser);
        return testUser;
    }

    @CacheEvict(value = "test", key = "#userId")
    @Override
    public int updateUserNickname(long userId, String nickname) {
        int effect = testUserDao.updateUserById(userId, nickname);
        if (effect > 0) {
            log.info("更新用户信息成功");
        } else
            log.info("更新失败");
        return effect;
    }

    @Override
    public testUserDTO login(LoginVO loginVO) {
        testUser testUser = testUserDao.getUserByUsername(loginVO.getNickname());
        if (testUser == null || !testUser.getPassword().equals(loginVO.getPassword())) {
            throw new ButinException(Status.LOGIN_FAILURE);
        }
        return new testUserDTO(testUser.getId(), testUser.getNickname(), testUser.getMobile(), testUser.getRole());
    }


}
