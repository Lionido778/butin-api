package cn.codeprobe.butin.service;

import cn.codeprobe.butin.model.dto.CategoryDTO;

import java.util.List;

/**
 * Created by Lionido on 16/2/2022
 */
public interface CategoryService {
    List<CategoryDTO> findCategories();

    CategoryDTO findCategory(Long id);
}
