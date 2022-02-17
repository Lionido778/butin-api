package cn.codeprobe.butin.service;

import cn.codeprobe.butin.model.po.Tag;

import java.util.List;

/**
 * Created by Lionido on 16/2/2022
 */
public interface TagService {

    List<Tag> findAll();

    List<Tag> findTagsByArticleId(long articleId);
}
