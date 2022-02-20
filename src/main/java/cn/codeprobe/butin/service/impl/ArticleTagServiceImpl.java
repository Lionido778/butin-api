package cn.codeprobe.butin.service.impl;

import cn.codeprobe.butin.model.po.ArticleTag;
import cn.codeprobe.butin.repository.ArticleTagDao;
import cn.codeprobe.butin.service.ArticleTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Lionido on 19/2/2022
 */
@Service("articleTagService")
public class ArticleTagServiceImpl implements ArticleTagService {

    @Resource
    private ArticleTagDao articleTagDao;

    @Override
    public Long add(Long articleId, Long tagId) {
        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleId(articleId);
        articleTag.setTagId(tagId);
        articleTag.setDeleted(true);
        return articleTagDao.insert(articleTag);
    }
}
