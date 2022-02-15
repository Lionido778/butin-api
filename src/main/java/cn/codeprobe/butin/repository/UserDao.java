package cn.codeprobe.butin.repository;

import cn.codeprobe.butin.model.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User getUserById(long userId);

    int updateUserById(long userId, String nickname);

    User getUserByUsername(String nickname);
}