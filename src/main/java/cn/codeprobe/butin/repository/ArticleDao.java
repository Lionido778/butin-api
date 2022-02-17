package cn.codeprobe.butin.repository;

import cn.codeprobe.butin.model.po.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleDao {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long id);

    List<Article> select();

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);
}