package cn.codeprobe.butin.service;

import cn.codeprobe.butin.model.dto.testUserDTO;
import cn.codeprobe.butin.model.po.testUser;
import cn.codeprobe.butin.model.vo.LoginVO;

/**
 * Created by Lionido on 13/2/2022
 */
public interface TestUserService {

    testUser getUserById(long userId);

    int updateUserNickname(long userId, String nickname);

    testUserDTO login(LoginVO loginVO);

}
