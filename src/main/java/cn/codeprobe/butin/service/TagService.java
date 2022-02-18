package cn.codeprobe.butin.service;

import cn.codeprobe.butin.model.dto.TagDTO;
import cn.codeprobe.butin.model.po.Tag;

import java.util.List;

/**
 * Created by Lionido on 16/2/2022
 */
public interface TagService {

    List<TagDTO> findTags();

    List<Tag> findTagsByArticleId(long articleId);

    List<TagDTO> findTagsHot(int rank);

    TagDTO findTag(Long id);
}
