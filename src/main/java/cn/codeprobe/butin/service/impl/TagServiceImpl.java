package cn.codeprobe.butin.service.impl;

import cn.codeprobe.butin.model.dto.TagDTO;
import cn.codeprobe.butin.model.po.Tag;
import cn.codeprobe.butin.repository.TagDao;
import cn.codeprobe.butin.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lionido on 16/2/2022
 */
@Service("tagService")
public class TagServiceImpl implements TagService {

    @Resource
    private TagDao tagDao;

    @Override
    public List<TagDTO> findTags() {
        List<Tag> tags = tagDao.select();
        ArrayList<TagDTO> tagDTOs = new ArrayList<>();
        for (Tag tag : tags) {
            TagDTO tagDTO = new TagDTO();
            BeanUtils.copyProperties(tag, tagDTO);
            tagDTOs.add(tagDTO);
        }
        return tagDTOs;
    }

    @Override
    public List<Tag> findTagsByArticleId(long articleId) {
        return null;
    }

    @Override
    public List<TagDTO> findTagsHot(int rank) {
        List<Long> ids = tagDao.selectHotTags(rank);
        Collections.sort(ids);
        System.out.println(ids);
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();
        for (Long id : ids) {
            TagDTO tagDTO = new TagDTO();
            Tag tag = tagDao.selectById(id);
            BeanUtils.copyProperties(tag, tagDTO);
            tagDTOS.add(tagDTO);
        }
        return tagDTOS;
    }

    @Override
    public TagDTO findTag(Long id) {
        Tag tag = tagDao.selectById(id);
        TagDTO tagDTO = new TagDTO();
        BeanUtils.copyProperties(tag, tagDTO);
        return tagDTO;
    }
}
