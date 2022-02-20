package cn.codeprobe.butin.service;

import cn.codeprobe.butin.model.po.User;
import cn.codeprobe.butin.model.vo.LoginVO;
import cn.codeprobe.butin.model.vo.RegisterVO;

/**
 * Created by Lionido on 16/2/2022
 */
public interface UserService {

    User findUserById(long userId);

    void register(RegisterVO registerVO);

    Long login(LoginVO loginVO);

}
