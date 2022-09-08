package com.lht.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.lht.pojo.dto.DishDto;
import com.lht.pojo.entity.Category;
import com.lht.pojo.entity.Dish;
import com.lht.service.DishService;
import com.lht.mapper.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 李
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2022-09-07 10:23:45
*/
@Service
public class DishServiceImpl extends MPJBaseServiceImpl<DishMapper, Dish>
    implements DishService{

    @Autowired
    private DishService dishService;

    @Autowired
    private DishMapper dishMapper;

    /**
     * 分页查询菜品
     * @param pageInfo
     * @param dish
     * @return
     */
    @Override
    public IPage findByPage(Page<DishDto> pageInfo, Dish dish) {

        MPJLambdaWrapper<Dish> wrapper = new MPJLambdaWrapper<>();

        dishMapper.selectJoinPage(pageInfo, DishDto.class, wrapper
                .selectAll(Dish.class)
                .select(Category::getName)
                .selectAs(Category::getName, DishDto::getCategoryName)
                .leftJoin(Category.class, Category::getId, Dish::getCategoryId)
                .like(dish.getName() != null, Dish::getName, dish.getName())
                .orderByAsc(Dish::getSort)
                .orderByDesc(Dish::getUpdateTime)
        );



        return pageInfo;
    }
}




