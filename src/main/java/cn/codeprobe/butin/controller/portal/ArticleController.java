package cn.codeprobe.butin.controller.portal;

import cn.codeprobe.butin.common.constant.PageParam;
import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.model.vo.*;
import cn.codeprobe.butin.service.ArticleService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Lionido on 16/2/2022
 */

@Api("文章相关API")
@RequestMapping("/portal")
@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;

//    @ApiOperation("获取文章列表")
//    @GetMapping("/articles/{page}/{pageSize}")
//    public R getArticleList(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {
//        PageHelper.startPage(page, pageSize);
//        PageHelper.orderBy("create_date desc");
//        List<ArticleDTO> articleDTOList = articleService.findArticles();
//        return R.ok(Status.OK).put("data", articleDTOList);
//    }

    @ApiOperation("获取文章列表")
    @PostMapping("/articles")
    public R getArticleList(@RequestBody PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());
        PageHelper.orderBy("create_date desc");
        List<ArticleListVO> articleListVOList = articleService.findArticles();
        return R.ok(Status.OK).put("data", articleListVOList);
    }

    @ApiOperation("浏览文章")
    @GetMapping("/articles/view/{id}")
    public R getViewArticle(@PathVariable("id") Long id) {
        ArticleViewVO articleViewVO = articleService.findArticleById(id);
        return R.ok(Status.OK).put("data", articleViewVO);
    }

    @ApiOperation("编辑文章")
    @GetMapping("/article/{id}")
    public R getArticle(@PathVariable("id") Long id) {
        return getViewArticle(id);
    }


    @ApiOperation("标签下的文章列表")
    @GetMapping("/articles/tag/{tagId}")
    public R getArticleByTag(@PathVariable("tagId") Long tagId) {
        List<ArticleListVO> articleListVOS = articleService.findArticleByTagId(tagId);
        return R.ok(Status.OK).put("data", articleListVOS);
    }


    @ApiOperation("分类的文章列表")
    @GetMapping("/articles/category/{categoryId}")
    public R getArticleByCategory(@PathVariable("categoryId") Long categoryId) {
        List<ArticleListVO> articleListVOS = articleService.findArticleByCategoryId(categoryId);
        return R.ok(Status.OK).put("data", articleListVOS);
    }


    @ApiOperation("获取最热文章")
    @GetMapping("/articles/hot/{limit}")
    public R getArticleHot(@PathVariable("limit") int limit) {
        List<ArticleHotVO> articleHotVOS = articleService.findArticleHot(limit);
        return R.ok(Status.OK).put("data", articleHotVOS);
    }

    @ApiOperation("获取最新文章")
    @GetMapping("/articles/new/{limit}")
    public R getArticleNew(@PathVariable("limit") int limit) {
        List<ArticleNewVO> articleNewVOS = articleService.findArticleNew(limit);
        return R.ok(Status.OK).put("data", articleNewVOS);
    }

    @ApiOperation("获取文章归档")
    @GetMapping("/articles/listArchives")
    public R getArticleArchivesList() {
        List<ArticleArchiveVO> articleArchives = articleService.findArticleArchives();
        return R.ok(Status.OK).put("data", articleArchives);
    }


}
