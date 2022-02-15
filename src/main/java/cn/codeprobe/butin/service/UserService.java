package cn.codeprobe.butin.service;

import cn.codeprobe.butin.model.dto.UserDTO;
import cn.codeprobe.butin.model.po.User;
import cn.codeprobe.butin.model.vo.LoginVO;

/**
 * Created by Lionido on 13/2/2022
 */
public interface UserService {

    User getUserById(long userId);

    int updateUserNickname(long userId, String nickname);

    UserDTO login(LoginVO loginVO);

}
