package cn.codeprobe.butin.repository;

import cn.codeprobe.butin.model.po.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagDao {
    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    int insertSelective(Tag record);

    //查询所有标签
    List<Tag> select();

    //查询标签多个id
    List<Tag> selectByIds(List<Long> ids);

    //更具id查询标签
    Tag selectById(Long id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    List<Long> selectHotTags(int rank);
}