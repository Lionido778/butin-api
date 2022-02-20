package cn.codeprobe.butin.repository;

import cn.codeprobe.butin.model.po.ArticleTag;

import java.util.List;

public interface ArticleTagDao {

    Long insert(ArticleTag record);

    //根据文章id获取标签
    List<Long> selectTagsByArticleId(long articleId);

    List<Long> selectArticleByTagId(Long TagId);

    Long deleteByModifyDeleted(Long articleId);
}