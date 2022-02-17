package cn.codeprobe.butin.repository;

import cn.codeprobe.butin.model.po.ArticleBody;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleBodyDao {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleBody record);

    int insertSelective(ArticleBody record);

    //通过
    ArticleBody selectById(Long id);

    //通过articleId
    ArticleBody selectByArticleId(Long articleId);

    int updateByPrimaryKeySelective(ArticleBody record);

    int updateByPrimaryKey(ArticleBody record);
}