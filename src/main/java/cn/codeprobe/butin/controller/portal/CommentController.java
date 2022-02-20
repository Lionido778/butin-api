package cn.codeprobe.butin.controller.portal;

import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.model.vo.CommentListVO;
import cn.codeprobe.butin.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Lionido on 17/2/2022
 */

@RequestMapping("/portal")
@RestController
public class CommentController {


    @Resource
    private CommentService commentService;

    @GetMapping("/comments/article/{articleId}")
    public R getArticleComment(@PathVariable("articleId") Long articleId) {
        List<CommentListVO> articleComments = commentService.findArticleComments(articleId);
        return R.ok(Status.OK).put("data", articleComments);
    }


}
