package cn.codeprobe.butin.repository;

import cn.codeprobe.butin.model.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface UserDao {

    int insert(User record);

    User selectById(Long id);

    Long selectIdByStr(String str);

    User selectUserByStr(String str);

    void updateLastLogin(Long id, LocalDateTime lastTime);
}