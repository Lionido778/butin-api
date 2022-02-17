package cn.codeprobe.butin.service;

import cn.codeprobe.butin.model.dto.CommentDTO;

import java.util.List;

/**
 * Created by Lionido on 17/2/2022
 */


public interface CommentService {
    List<CommentDTO> findArticleComments(Long articleId);
}
