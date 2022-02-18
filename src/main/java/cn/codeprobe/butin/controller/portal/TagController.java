package cn.codeprobe.butin.controller.portal;

import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.model.dto.TagDTO;
import cn.codeprobe.butin.service.TagService;
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

//    @ApiOperation("")
//    @GetMapping("/tags/{page}/{pageSize}")
//    public R getTags(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {
//        PageHelper.startPage(page, pageSize);
//        List<Tag> tags = tagService.findAll();
//        return R.ok(Status.OK).put("data", tags);
//    }

    @ApiOperation("获取所有标签")
    @GetMapping("/tags/detail")
    public R getTagList() {
        List<TagDTO> tags = tagService.findTags();
        return R.ok(Status.OK).put("data", tags);
    }

    @ApiOperation("获取标签")
    @GetMapping("/tags/detail/{id}")
    public R getTag(@PathVariable("id") Long id) {
        TagDTO tagDTO = tagService.findTag(id);
        return R.ok(Status.OK).put("data", tagDTO);
    }


    @ApiOperation("获取最热标签")
    @GetMapping("/tags/hot/{rank}")
    public R getTagsHot(@PathVariable("rank") int rank) {
        List<TagDTO> hotTags = tagService.findTagsHot(rank);
        return R.ok(Status.OK).put("data", hotTags);
    }

    @ApiOperation("获取最新标签")
    @GetMapping("/tags/new")
    public R getTagsNew() {
        return R.ok(Status.OK).put("data", null);
    }

}
