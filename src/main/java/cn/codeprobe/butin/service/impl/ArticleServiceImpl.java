package cn.codeprobe.butin.service.impl;

import cn.codeprobe.butin.common.exception.ButinException;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.model.dto.UserDTO;
import cn.codeprobe.butin.model.po.*;
import cn.codeprobe.butin.model.vo.*;
import cn.codeprobe.butin.repository.*;
import cn.codeprobe.butin.service.ArticleCategoryService;
import cn.codeprobe.butin.service.ArticleService;
import cn.codeprobe.butin.service.ArticleTagService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Resource
    private ArticleTagService articleTagService;

    @Resource
    private ArticleCategoryService articleCategoryService;


    public List<ArticleListVO> findArticles() {
        ArrayList<ArticleListVO> articleListVOS = new ArrayList<>();
        List<Article> articles = articleDao.select();
        if (articles == null) {
            throw new ButinException(Status.ARTICLE_LIST_NULL);
        }
        for (Article article : articles) {
            ArticleListVO articleListVO = Article2VO(article);
            articleListVOS.add(articleListVO);
        }
        return articleListVOS;
    }

    @Override
    public ArticleViewVO findArticleById(Long id) {
        Article article = articleDao.selectById(id);
        if (article == null) {
            throw new ButinException(Status.ARTICLE_NULL);
        }
        ArticleListVO articleListVO = Article2VO(article);
        ArticleViewVO articleViewVO = new ArticleViewVO();
        BeanUtils.copyProperties(articleListVO, articleViewVO);
        articleViewVO.setBody(articleBodyDao.selectById(article.getBodyId()));
        List<Long> categoryIds = articleCategoryDao.selectCategoryByArticleId(article.getId());
        articleViewVO.setCategories(categoryDao.selectByIds(categoryIds));
        return articleViewVO;
    }

    @Override
    public List<ArticleListVO> findArticleByTagId(Long tagId) {
        List<Long> articleIds = articleTagDao.selectArticleByTagId(tagId);
        List<Article> articles = articleDao.selectByIds(articleIds);
        ArrayList<ArticleListVO> articleListVOS = new ArrayList<>();
        for (Article article : articles) {
            articleListVOS.add(Article2VO(article));
        }
        return articleListVOS;
    }

    @Override
    public List<ArticleListVO> findArticleByCategoryId(Long categoryId) {
        List<Long> articleIds = articleCategoryDao.selectArticleByCategoryId(categoryId);
        List<Article> articles = articleDao.selectByIds(articleIds);
        ArrayList<ArticleListVO> articleListVOS = new ArrayList<>();
        for (Article article : articles) {
            articleListVOS.add(Article2VO(article));
        }
        return articleListVOS;
    }

    @Override
    public List<ArticleHotVO> findArticleHot(int limit) {
        return articleDao.selectByViewCountAndComment(limit);
    }

    @Override
    public List<ArticleNewVO> findArticleNew(int limit) {
        return articleDao.selectByCreateTime(limit);
    }

    @Override
    public List<ArticleArchiveVO> findArticleArchives() {
        List<ArticleArchiveVO> articleArchiveVOS = articleDao.selectArchives();
        ArrayList<ArticleArchiveVO> newArchivesVO = new ArrayList<>();
        for (ArticleArchiveVO archiveVO : articleArchiveVOS) {
            int num = articleDao.selectNumByYearAndMonth(archiveVO.getYear(), archiveVO.getMonth());
            archiveVO.setCount(num);
            newArchivesVO.add(archiveVO);
        }
        return newArchivesVO;
    }


    @Override
    public Long addArticle(ArticlePublishVO articlePublishVO) {
        Article article = new Article();
        BeanUtils.copyProperties(articlePublishVO, article);
        //????????????????????????
        UserDTO subject = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        article.setAuthorId(subject.getId());
        article.setCreateDate(LocalDateTime.now());
        //????????????
        article.setWeight(false);
        //????????????body,??????bodyId
        ArticleBody articleBody = new ArticleBody();
        articleBody.setContent(articlePublishVO.getBody().getContent());
        articleBody.setContentHtml(articlePublishVO.getBody().getContentHtml());
        articleBody.setDeleted(true);
        articleBodyDao.insert(articleBody);
        article.setBodyId(articleBody.getId());
        //???????????? 2 ?????????
        article.setStatus(2);
        //????????? ???????????????????????????
        article.setViewCounts(0);
        article.setCommentCounts(0);
        //????????????
        articleDao.insert(article);
        // ?????????mapper???????????????????????????????????????????????????????????????????????????????????????????????????????????????id??????,getId ?????????
        Long articleId = article.getId();
        //??????????????????
        List<Long> tagIds = articlePublishVO.getTagIds();
        for (Long tagId : tagIds) {
            articleTagService.add(articleId, tagId);
        }
        //??????????????????
        List<Long> categoryIds = articlePublishVO.getCategoryIds();
        for (Long categoryId : categoryIds) {
            articleCategoryService.add(articleId, categoryId);
        }
        return articleId;
    }

    @Override
    public void addCommentCount(Long articleId) {
        articleDao.updateCommentCount(articleId);
    }

    @Resource
    private ArticleVersionDao articleVersionDao;

    @Resource
    private CommentDao commentDao;

    @Override
    public Long updateArticleById(ArticlePublishVO articlePublishVO) {
        //???????????? (?????????????????????????????????body)
        articleDao.deleteByModifyStatus(articlePublishVO.getId());
        articleCategoryDao.deleteByModifyDeleted(articlePublishVO.getId());
        articleTagDao.deleteByModifyDeleted(articlePublishVO.getId());
        //??????????????????
        Article oldArticle = articleDao.selectById(articlePublishVO.getId());
        articleBodyDao.deleteByModifyDeleted(oldArticle.getBodyId());
        //??????????????????????????????oldArticle????????? viewCounts,commentCounts,weight
        Long newArticleId = addArticle(articlePublishVO);
        articleDao.updateNewArticleParms(oldArticle.getCommentCounts(), oldArticle.getViewCounts(), oldArticle.getWeight()
                , newArticleId);
        //????????????
        commentDao.updateByArticleId(articlePublishVO.getId(), newArticleId);
        //??????????????????
        ArticleVersion articleVersion = new ArticleVersion();
        articleVersion.setOldArticleId(articlePublishVO.getId());
        articleVersion.setNewArticleId(newArticleId);
        articleVersionDao.insert(articleVersion);
        return newArticleId;
    }


    private ArticleListVO Article2VO(Article article) {
        User user = userDao.selectById(article.getAuthorId());
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        List<Long> tagIds = articleTagDao.selectTagsByArticleId(article.getId());
        List<Tag> tags = tagDao.selectByIds(tagIds);

        ArticleListVO articleListVO = new ArticleListVO();
        articleListVO.setCommentCounts(article.getCommentCounts());
        articleListVO.setCreateDate(article.getCreateDate());
        articleListVO.setId(article.getId());
        articleListVO.setSummary(article.getSummary());
        articleListVO.setTags(tags);
        articleListVO.setTitle(article.getTitle());
        articleListVO.setUser(userDTO);
        articleListVO.setViewCounts(article.getViewCounts());
        articleListVO.setWeight(article.getWeight());
        return articleListVO;
    }
}
