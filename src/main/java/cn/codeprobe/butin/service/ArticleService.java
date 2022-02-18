package cn.codeprobe.butin.service;

import cn.codeprobe.butin.model.dto.ArticleDTO;

import java.util.List;

/**
 * Created by Lionido on 16/2/2022
 */
public interface ArticleService {

    List<ArticleDTO> findArticles();

    ArticleDTO findArticleById(Long id);

    List<ArticleDTO> findArticleByTagId(Long id);

    List<ArticleDTO> findArticleByCategoryId(Long categoryId);
}
