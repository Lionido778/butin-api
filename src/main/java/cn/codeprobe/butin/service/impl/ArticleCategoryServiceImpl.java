package cn.codeprobe.butin.service.impl;

import cn.codeprobe.butin.model.po.ArticleCategory;
import cn.codeprobe.butin.repository.ArticleCategoryDao;
import cn.codeprobe.butin.service.ArticleCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Lionido on 19/2/2022
 */
@Service("articleCategoryService")
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    @Resource
    private ArticleCategoryDao articleCategoryDao;

    @Override
    public Long add(Long articleId, Long categoryId) {
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setArticleId(articleId);
        articleCategory.setCategoryId(categoryId);
        articleCategory.setDeleted(true);
        return articleCategoryDao.insert(articleCategory);
    }
}
