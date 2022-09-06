package com.lht.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lht.pojo.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 李
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service
* @createDate 2022-09-06 14:06:19
*/
public interface CategoryService extends IService<Category> {

    /**
     * 分页查询所有分类的接口
     * @param category
     * @param pageInfo
     * @return
     */
    IPage pageAll(Category category, Page<Category> pageInfo);

}
