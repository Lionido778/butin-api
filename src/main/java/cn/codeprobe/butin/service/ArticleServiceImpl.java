package cn.codeprobe.butin.service;

import cn.codeprobe.butin.common.exception.ButinException;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.model.dto.ArticleDTO;
import cn.codeprobe.butin.model.dto.UserDTO;
import cn.codeprobe.butin.model.po.*;
import cn.codeprobe.butin.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lionido on 16/2/2022
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;

    @Resource
    private ArticleCategoryDao articleCategoryDao;

    @Resource
    private CategoryDao categoryDao;

    @Resource
    private TagDao tagDao;

    @Resource
    private ArticleTagDao articleTagDao;

    @Resource
    private ArticleBodyDao articleBodyDao;

    @Resource
    private UserDao userDao;


    public List<ArticleDTO> findArticles() {
        ArrayList<ArticleDTO> articleDTOs = new ArrayList<>();
        List<Article> articles = articleDao.select();
        if (articles == null) {
            throw new ButinException(Status.ARTICLE_LIST_NULL);
        }
        for (Article article : articles) {
            ArticleDTO articleDTO = Article2DTO(article);
            articleDTOs.add(articleDTO);
        }
        return articleDTOs;
    }

    @Override
    public ArticleDTO findArticleById(Long id) {
        Article article = articleDao.selectByPrimaryKey(id);
        return Article2DTO(article);
    }

    @Override
    public List<ArticleDTO> findArticleByTagId(Long tagId) {
        List<Long> articleIds = articleTagDao.selectArticleByTagId(tagId);
        List<Article> articles = articleDao.selectByIds(articleIds);
        ArrayList<ArticleDTO> articleDTOS = new ArrayList<>();
        for (Article article : articles) {
            articleDTOS.add(Article2DTO(article));
        }
        return articleDTOS;
    }

    @Override
    public List<ArticleDTO> findArticleByCategoryId(Long categoryId) {
        List<Long> articleIds = articleCategoryDao.selectArticleByCategoryId(categoryId);
        List<Article> articles = articleDao.selectByIds(articleIds);
        ArrayList<ArticleDTO> articleDTOS = new ArrayList<>();
        for (Article article : articles) {
            articleDTOS.add(Article2DTO(article));
        }
        return articleDTOS;
    }


    private ArticleDTO Article2DTO(Article article) {
        User user = userDao.selectById(article.getAuthorId());
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        ArticleBody articleBody = articleBodyDao.selectById(article.getBodyId());
        List<Long> tagIds = articleTagDao.selectTagsByArticleId(article.getId());
        List<Tag> tags = tagDao.selectByIds(tagIds);
        List<Long> articleIds = articleCategoryDao.selectCategoryByArticleId(article.getId());
        List<Category> categories = categoryDao.selectByIds(articleIds);
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setArticleBody(articleBody);
        articleDTO.setCategories(categories);
        articleDTO.setCommentCounts(article.getCommentCounts());
        articleDTO.setCreateDate(article.getCreateDate());
        articleDTO.setId(article.getId());
        articleDTO.setSummary(article.getSummary());
        articleDTO.setTags(tags);
        articleDTO.setTitle(article.getTitle());
        articleDTO.setUser(userDTO);
        articleDTO.setViewCounts(article.getViewCounts());
        articleDTO.setWeight(article.getWeight());
        return articleDTO;
    }
}
