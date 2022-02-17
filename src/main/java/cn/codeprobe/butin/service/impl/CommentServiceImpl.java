package cn.codeprobe.butin.service.impl;

import cn.codeprobe.butin.model.dto.CommentDTO;
import cn.codeprobe.butin.model.po.Comment;
import cn.codeprobe.butin.model.po.User;
import cn.codeprobe.butin.repository.CommentDao;
import cn.codeprobe.butin.repository.UserDao;
import cn.codeprobe.butin.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lionido on 17/2/2022
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    @Resource
    private UserDao userDao;


    @Override
    public List<CommentDTO> findArticleComments(Long articleId) {
        List<Comment> comments = commentDao.selectByArticleId(articleId);
        ArrayList<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            User user = userDao.selectById(comment.getAuthorId());
            User toUser = userDao.selectById(comment.getToUid());
            commentDTO.setUser(user);
            commentDTO.setToUser(toUser);
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;
    }
}
