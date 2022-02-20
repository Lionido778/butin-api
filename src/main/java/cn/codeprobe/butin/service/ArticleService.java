package cn.codeprobe.butin.service;

import cn.codeprobe.butin.model.vo.*;

import java.util.List;

/**
 * Created by Lionido on 16/2/2022
 */
public interface ArticleService {

    List<ArticleListVO> findArticles();

    ArticleViewVO findArticleById(Long id);

    List<ArticleListVO> findArticleByTagId(Long id);

    List<ArticleListVO> findArticleByCategoryId(Long categoryId);

    List<ArticleHotVO> findArticleHot(int limit);

    List<ArticleNewVO> findArticleNew(int limit);

    List<ArticleArchiveVO> findArticleArchives();

    Long addArticle(ArticlePublishVO articlePublishVO);

    void addCommentCount(Long articleId);

    Long updateArticleById(ArticlePublishVO articlePublishVO);
}
