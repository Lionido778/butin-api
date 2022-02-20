package cn.codeprobe.butin.repository;

import cn.codeprobe.butin.model.po.Article;
import cn.codeprobe.butin.model.vo.ArticleArchiveVO;
import cn.codeprobe.butin.model.vo.ArticleHotVO;
import cn.codeprobe.butin.model.vo.ArticleNewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleDao {

    Long insert(Article record);

    Article selectById(Long id);

    List<Article> select();

    List<Article> selectByIds(List<Long> articleIds);

    List<ArticleHotVO> selectByViewCountAndComment(int limit);

    List<ArticleNewVO> selectByCreateTime(int limit);

    List<ArticleArchiveVO> selectArchives();

    int selectNumByYearAndMonth(String year, String month);

    void updateCommentCount(Long articleId);

    Long deleteByModifyStatus(Long id);

    void updateNewArticleParms(Integer commentCounts, Integer viewCounts, Boolean weight, Long newArticleId);
}