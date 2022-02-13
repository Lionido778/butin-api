package cn.codeprobe.butin.service;

import cn.codeprobe.butin.pojo.po.User;

/**
 * Created by Lionido on 13/2/2022
 */
public interface UserService {

    User getUserById(long userId);

    User updateUserNickname(long userId, String nickname);

}
