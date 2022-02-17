package cn.codeprobe.butin.service;

import cn.codeprobe.butin.model.po.User;

/**
 * Created by Lionido on 16/2/2022
 */
public interface UserService {

    User findUserById(long userId);
}
