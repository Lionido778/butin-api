package cn.codeprobe.butin.repository;

import cn.codeprobe.butin.model.po.ArticleVersion;

public interface ArticleVersionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleVersion record);

    int insertSelective(ArticleVersion record);

    ArticleVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleVersion record);

    int updateByPrimaryKey(ArticleVersion record);
}