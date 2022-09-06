package com.lht.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lht.pojo.entity.Category;
import com.lht.pojo.entity.Employee;
import com.lht.service.CategoryService;
import com.lht.mapper.CategoryMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author 李
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
* @createDate 2022-09-06 14:06:18
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Override
    public IPage pageAll(Category category, Page<Category> pageInfo) {
        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();

        lqw.orderByDesc(Category::getSort);

        Page<Category> pageData = page(pageInfo, lqw);

        return pageData;
    }
}




