package cn.codeprobe.butin.controller.portal;

import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.model.po.Tag;
import cn.codeprobe.butin.service.TagService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Lionido on 16/2/2022
 */
@Api("标签相关")
@RequestMapping("/portal")
@RestController
public class TagController {

    @Resource
    private TagService tagService;

    @ApiOperation("")
    @GetMapping("/tags/{page}/{pageSize}")
    public R getTags(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Tag> tags = tagService.findAll();
        return R.ok(Status.OK).put("data", tags);
    }

    @ApiOperation("获取最新标签")
    @GetMapping("/tags/new")
    public R getArticleNew() {
        return R.ok(Status.OK).put("data", null);
    }

    @ApiOperation("获取最热标签")
    @GetMapping("/tags/hot")
    public R getArticleHot() {
        return R.ok(Status.OK).put("data", null);
    }

}
