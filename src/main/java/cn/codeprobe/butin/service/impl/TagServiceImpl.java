package cn.codeprobe.butin.service.impl;

import cn.codeprobe.butin.model.po.Tag;
import cn.codeprobe.butin.repository.TagDao;
import cn.codeprobe.butin.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Lionido on 16/2/2022
 */
@Service("tagService")
public class TagServiceImpl implements TagService {

    @Resource
    private TagDao tagDao;

    @Override
    public List<Tag> findAll() {
        return tagDao.select();
    }

    @Override
    public List<Tag> findTagsByArticleId(long articleId) {
        return null;
    }
}
