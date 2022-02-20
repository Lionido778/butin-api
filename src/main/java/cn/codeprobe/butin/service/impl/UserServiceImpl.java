package cn.codeprobe.butin.service.impl;

import cn.codeprobe.butin.common.exception.ButinException;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.model.po.User;
import cn.codeprobe.butin.model.vo.LoginVO;
import cn.codeprobe.butin.model.vo.RegisterVO;
import cn.codeprobe.butin.repository.UserDao;
import cn.codeprobe.butin.service.UserService;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Override
    public void register(RegisterVO registerVO) {
        //根据 昵称，邮箱，手机号 查重
        isRepeat(registerVO.getNickname(), registerVO.getEmail(), registerVO.getMobilePhoneNumber());
        User user = new User();
        BeanUtils.copyProperties(registerVO, user);
        //密码盐
        String salt = new RandomGenerator(6).generate();
        String password = user.getPassword();
        String encrypt = getEncrypt(salt, password);
        //密码加盐加密
        user.setPassword(encrypt);
        String randomStr = new RandomGenerator(6).generate();
        String mMdd = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        //生成account
        user.setAccount("butin_" + mMdd + randomStr);
        user.setCreateDate(LocalDateTime.now());
        user.setSalt(salt);
        user.setAdmin(false);
        RandomUtil.randomInt(1, 6);
        user.setAvatar("/static/user/user_" + RandomUtil.randomInt(1, 6) + ".png");
        user.setDeleted(false);
        user.setStatus(true);
        userDao.insert(user);
    }

    @Override
    public Long login(LoginVO loginVO) {
        User user = userDao.selectUserByStr(loginVO.getNickname());
        if (user == null) {
            throw new ButinException(Status.ACCOUNT_NULL);
        }
        if (user.getDeleted()) {
            throw new ButinException(Status.ACCOUNT_LOCKED);
        }
        String encrypt = getEncrypt(user.getSalt(), loginVO.getPassword());
        if (!encrypt.equals(user.getPassword())) {
            throw new ButinException(Status.PASSWORD_INCORRECT);
        }
        userDao.updateLastLogin(user.getId(), LocalDateTime.now());
        return user.getId();
    }


    private void isRepeat(String... str) {
        for (String s : str) {
            Long id = userDao.selectIdByStr(s);
            if (id != null) {
                throw new ButinException(Status.ACCOUNT_REPEAT);
            }
        }
    }

    private String getEncrypt(String salt, String password) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes(StandardCharsets.UTF_8));
    }
}
