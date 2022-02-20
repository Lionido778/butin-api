package cn.codeprobe.butin.service.impl;

import cn.codeprobe.butin.repository.ArticleBodyDao;
import cn.codeprobe.butin.service.ArticleBodyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Lionido on 16/2/2022
 */
@Service("articleBodyService")
public class ArticleBodyServiceImpl implements ArticleBodyService {

    @Resource
    private ArticleBodyDao articleBodyDao;

//    @Override
//    public ArticleBody findByArticleId(long ArticleId) {
//        return articleBodyDao.selectByArticleId(ArticleId);
//    }
}
