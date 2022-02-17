package cn.codeprobe.butin.repository;

import cn.codeprobe.butin.model.po.testUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestUserDao {


    testUser getUserById(long userId);

    int updateUserById(long userId, String nickname);

    testUser getUserByUsername(String nickname);
}