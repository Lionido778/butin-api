package cn.codeprobe.butin.controller.portal;

import cn.codeprobe.butin.common.response.R;
import cn.codeprobe.butin.common.response.Status;
import cn.codeprobe.butin.model.dto.CategoryDTO;
import cn.codeprobe.butin.service.CategoryService;
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
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/categories/detail")
    public R getCategoryList() {
        List<CategoryDTO> categoryDTOS = categoryService.findCategories();
        return R.ok(Status.OK).put("data", categoryDTOS);
    }

    @GetMapping("/categories/detail/{id}")
    public R getCategory(@PathVariable("id") Long id) {
        CategoryDTO categoryDTO = categoryService.findCategory(id);
        return R.ok(Status.OK).put("data", categoryDTO);
    }

    @GetMapping("/categories")
    public R getCategories() {
        List<CategoryDTO> categoryDTOs = categoryService.findCategories();
        return R.ok(Status.OK).put("data", categoryDTOs);
    }

}
