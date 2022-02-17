package cn.codeprobe.butin.service;

import cn.codeprobe.butin.model.po.ArticleBody;

/**
 * Created by Lionido on 16/2/2022
 */

public interface ArticleBodyService {

    ArticleBody findByArticleId(long ArticleId);
}
