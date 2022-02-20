package cn.codeprobe.butin.service.impl;

import cn.codeprobe.butin.model.dto.CommentDTO;
import cn.codeprobe.butin.model.dto.UserDTO;
import cn.codeprobe.butin.model.po.Comment;
import cn.codeprobe.butin.model.po.User;
import cn.codeprobe.butin.model.vo.CommentListVO;
import cn.codeprobe.butin.model.vo.CommentPublishVO;
import cn.codeprobe.butin.repository.CommentDao;
import cn.codeprobe.butin.repository.UserDao;
import cn.codeprobe.butin.service.ArticleService;
import cn.codeprobe.butin.service.CommentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Resource
    private ArticleService articleService;


    @Override
    public List<CommentListVO> findArticleComments(Long articleId) {
        //第一级别评论（没有回复的评论）
        List<Comment> comments = commentDao.selectByArticleId(articleId);
        if (comments == null) {
            return null;
        }
        //entity2DTO
        ArrayList<CommentDTO> commentDTOS = new ArrayList<>();
        comment2DTO(comments, commentDTOS);
        //commentVOs
        ArrayList<CommentListVO> commentListVOS = new ArrayList<>();
        for (CommentDTO commentDTO : commentDTOS) {
            //拿到子评论
            List<Comment> childrenComment = commentDao.selectCommentChildren(articleId, commentDTO.getId());
            ArrayList<CommentDTO> childrenCommentDTO = new ArrayList<>();
            comment2DTO(childrenComment, childrenCommentDTO);
            //dto2vo
            CommentListVO commentListVO = new CommentListVO();
            commentListVO.setChildrens(childrenCommentDTO);
            BeanUtils.copyProperties(commentDTO, commentListVO);
            commentListVOS.add(commentListVO);
        }
        return commentListVOS;
    }

    private void comment2DTO(List<Comment> comments, ArrayList<CommentDTO> commentDTOS) {
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            //评论人
            User user = userDao.selectById(comment.getAuthorId());
            if (user != null) {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(user, userDTO);
                commentDTO.setUser(userDTO);
            }
            //被评论人
            User toUser = userDao.selectById(comment.getToUid());
            if (toUser != null) {
                UserDTO toUserDTO = new UserDTO();
                BeanUtils.copyProperties(toUser, toUserDTO);
                commentDTO.setToUser(toUserDTO);
            }
            commentDTOS.add(commentDTO);
        }
    }


    @Override
    public CommentDTO createComment(CommentPublishVO commentPublishVO) {
        Long articleId = commentPublishVO.getArticleId();
        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        String content = commentPublishVO.getContent();
        Comment comment = new Comment();
        //被评论文章
        comment.setArticleId(articleId);
        //评论人
        comment.setAuthorId(principal.getId());
        //内容
        comment.setContent(content);
        //创建时间
        comment.setCreateDate(LocalDateTime.now());
        //评论文章 to_uid = 0
        if (commentPublishVO.getToUserId() == null && commentPublishVO.getParentId() == null) {
            comment.setToUid(0L);
            comment.setParentId(0L);
            comment.setLevel(1);
            //插入评论
            commentDao.insert(comment);
            //返回生成的评论 commentDTO
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(principal);
            //文章 评论数量+1
            articleService.addCommentCount(articleId);
            return commentDTO;
        }
        //回复评论
        comment.setToUid(commentPublishVO.getToUserId());
        comment.setParentId(commentPublishVO.getParentId());
        int parentLevel = commentDao.selectParentLevel(commentPublishVO.getParentId());
        comment.setLevel(parentLevel + 1);
        //插入评论
        commentDao.insert(comment);
        //返回生成的评论 commentDTO
        User toUser = userDao.selectById(commentPublishVO.getToUserId());
        UserDTO toUserDTO = new UserDTO();
        BeanUtils.copyProperties(toUser, toUserDTO);
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(comment, commentDTO);
        commentDTO.setUser(principal);
        commentDTO.setToUser(toUserDTO);
        //文章 评论数量+1
        articleService.addCommentCount(articleId);
        return commentDTO;
    }


}
