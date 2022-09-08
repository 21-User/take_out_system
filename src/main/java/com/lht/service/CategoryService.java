package com.lht.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseService;
import com.lht.pojo.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lht.pojo.vo.Result;

import java.util.List;

/**
* @author 李
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service
* @createDate 2022-09-06 14:06:19
*/
public interface CategoryService extends MPJBaseService<Category> {

    /**
     * 分页查询所有分类的接口
     * @param category
     * @param pageInfo
     * @return
     */
    IPage pageAll(Category category, Page<Category> pageInfo);

    /**
     * 修改分类管理的接口
     * @param category
     * @return
     */
    Result<Category> edit(Category category);

    /**
     * 删除分类接口
     * @param id
     * @return
     */
    Result<String> delete(Long id);

    /**
     * 获取菜品管理的分类名称接口
     * @param category
     * @return
     */
    Result<List<Category>> findType(Category category);
}
