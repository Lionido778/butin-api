package cn.codeprobe.butin.repository;

import cn.codeprobe.butin.model.po.ArticleBody;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleBodyDao {

    Long insert(ArticleBody record);

    ArticleBody selectById(Long id);

    Long deleteByModifyDeleted(Long bodyId);
}