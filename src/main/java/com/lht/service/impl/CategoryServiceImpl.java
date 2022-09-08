package com.lht.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.lht.exception.CustomException;
import com.lht.mapper.DishMapper;
import com.lht.mapper.SetmealMapper;
import com.lht.pojo.entity.Category;
import com.lht.pojo.entity.Dish;
import com.lht.pojo.entity.Employee;
import com.lht.pojo.entity.Setmeal;
import com.lht.pojo.vo.Result;
import com.lht.service.CategoryService;
import com.lht.mapper.CategoryMapper;
import com.lht.service.DishService;
import com.lht.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 李
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
* @createDate 2022-09-06 14:06:18
*/
@Service
@Slf4j
public class CategoryServiceImpl extends MPJBaseServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    /**
     * 注入菜品管理的接口
     */
    @Autowired
    private DishService dishService;

    /**
     * 注入套餐的接口
     */
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 分页查询分类管理的业务实现
     * @param category
     * @param pageInfo
     * @return
     */
    @Override
    public IPage pageAll(Category category, Page<Category> pageInfo) {
        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();

        lqw.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        Page<Category> pageData = page(pageInfo, lqw);

        return pageData;
    }

    /**
     * 修改分类管理的业务实现
     * @param category
     * @return
     */
    @Override
    public Result<Category> edit(Category category) {

        int affectedRows = categoryMapper.updateById(category);

        if (affectedRows > 0) {
            return Result.success(category);
        }

        return Result.error("修饰分类失败！");
    }

    @Override
    public Result<String> delete(Long id) {

        LambdaQueryWrapper<Dish> DishLqw = new LambdaQueryWrapper<>();

        //校验菜品表中的分类id是否和分类表中的id相等
        DishLqw.eq(Dish::getCategoryId, id);

        long dishCount = dishService.count(DishLqw);

        if (dishCount > 0) {
            throw new CustomException("当前菜品分类下还有关联的菜品，不能删除！");
        }

        LambdaQueryWrapper<Setmeal> lqw = new LambdaQueryWrapper<>();

        lqw.eq(Setmeal::getCategoryId, id);

        long count = setmealService.count(lqw);

        if (count > 0) {
            throw new CustomException("当前套餐分类下还有关联的套餐，不能删除！");
        }

        categoryMapper.deleteById(id);



        return Result.success("删除成功");
    }

    /**
     * 获取菜品分类的分类名称
     * @param category
     * @return
     */
    @Override
    public Result<List<Category>> findType(Category category) {

        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();

        lqw.eq(category.getType() != null, Category::getType, category.getType());

        //先根据sort值进行升序排序，相等的情况下再根据更新时间进行降序排序
        lqw.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(lqw);

        return Result.success(list);
    }
}




