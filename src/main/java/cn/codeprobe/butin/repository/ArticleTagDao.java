package cn.codeprobe.butin.repository;

import cn.codeprobe.butin.model.po.ArticleTag;

import java.util.List;

public interface ArticleTagDao {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleTag record);

    int insertSelective(ArticleTag record);

    ArticleTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleTag record);

    int updateByPrimaryKey(ArticleTag record);

    //根据文章id获取标签
    List<Long> selectTagsByArticleId(long articleId);

    List<Long> selectArticleByTagId(Long TagId);
}