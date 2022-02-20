package cn.codeprobe.butin.controller.user;

import cn.codeprobe.butin.common.exception.ButinException;
import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.common.security.threadlocal.ThreadLocalToken;
import cn.codeprobe.butin.common.utils.JwtUtil;
import cn.codeprobe.butin.model.dto.CommentDTO;
import cn.codeprobe.butin.model.dto.UserDTO;
import cn.codeprobe.butin.model.vo.ArticlePublishVO;
import cn.codeprobe.butin.model.vo.CommentPublishVO;
import cn.codeprobe.butin.model.vo.LoginVO;
import cn.codeprobe.butin.model.vo.RegisterVO;
import cn.codeprobe.butin.service.ArticleService;
import cn.codeprobe.butin.service.CommentService;
import cn.codeprobe.butin.service.UserService;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lionido on 18/2/2022
 */

@Api(value = "用户相关")
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private ThreadLocalToken threadLocalToken;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${butin.jwt.expireTime}")
    private Long expireTimeCache;

    @Resource
    private ArticleService articleService;

    @Resource
    private CommentService commentService;


    @ApiOperation("注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVO registerVO) {
        userService.register(registerVO);
        return R.ok(Status.OK).put("data", null);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVO loginVO) {
        Long userId = userService.login(loginVO);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        String token = jwtUtil.createToken(map);
        threadLocalToken.saveToken(token);
        redisTemplate.opsForValue().set(token, userId, expireTimeCache, TimeUnit.DAYS);
        return R.ok(Status.OK.setMsg("登陆成功")).put("data", userId);
    }


    @ApiOperation("获取当前用户")
    @GetMapping("/currentUser")
    public R getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        UserDTO userDTO = (UserDTO) subject.getPrincipal();
        if (userDTO == null) {
            throw new ButinException(Status.GET_CURRENT_USER_FAILURE);
        }
        return R.ok(Status.OK).put("data", userDTO);
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public R logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return R.ok(Status.OK.setMsg("退出成功"));
    }


    @ApiOperation("发表文章")
    @PostMapping("/article/publish")
    public R publish(@RequestBody ArticlePublishVO articlePublishVO) {
        boolean authenticated = SecurityUtils.getSubject().isAuthenticated();
        if (!authenticated) {
            throw new ButinException(Status.PUBLISH_FAILURE);
        }
        //更新文章
        if (articlePublishVO.getId() != null && StrUtil.isNotBlank(articlePublishVO.getId().toString())) {
            Long articleId = articleService.updateArticleById(articlePublishVO);
            return R.ok(Status.OK.setMsg("文章更新成功")).put("data", articleId);
        }
        Long articleId = articleService.addArticle(articlePublishVO);
        return R.ok(Status.OK.setMsg("发表成功")).put("data", articleId);
    }


    @ApiOperation("发表评论")
    @PostMapping("/comments/create/change")
    public R publishComment(@RequestBody CommentPublishVO commentPublishVO) {
        CommentDTO commentDTO = commentService.createComment(commentPublishVO);
        return R.ok(Status.OK).put("data", commentDTO);
    }


}
