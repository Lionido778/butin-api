package cn.codeprobe.butin.repository;

import cn.codeprobe.butin.model.po.ArticleCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleCategoryDao {

    Long insert(ArticleCategory record);

    List<Long> selectCategoryByArticleId(Long articleId);

    List<Long> selectArticleByCategoryId(Long categoryId);

    Long deleteByModifyDeleted(Long articleId);
}