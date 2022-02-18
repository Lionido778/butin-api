package cn.codeprobe.butin.service.impl;

import cn.codeprobe.butin.model.dto.CategoryDTO;
import cn.codeprobe.butin.model.po.Category;
import cn.codeprobe.butin.repository.CategoryDao;
import cn.codeprobe.butin.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lionido on 16/2/2022
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Override
    public List<CategoryDTO> findCategories() {
        List<Category> categories = categoryDao.select();
        ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : categories) {
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtils.copyProperties(category, categoryDTO);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    @Override
    public CategoryDTO findCategory(Long id) {
        Category category = categoryDao.selectById(id);
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDTO);
        return categoryDTO;
    }
}
