package cn.codeprobe.butin.repository;

import cn.codeprobe.butin.model.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectById(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}