package cn.codeprobe.butin.service;

import cn.codeprobe.butin.model.dto.CommentDTO;
import cn.codeprobe.butin.model.vo.CommentListVO;
import cn.codeprobe.butin.model.vo.CommentPublishVO;

import java.util.List;

/**
 * Created by Lionido on 17/2/2022
 */


public interface CommentService {

    List<CommentListVO> findArticleComments(Long articleId);

    CommentDTO createComment(CommentPublishVO commentPublishVO);
}
