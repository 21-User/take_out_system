package com.lht.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseService;
import com.lht.pojo.dto.DishDto;
import com.lht.pojo.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 分页查询菜品管理的接口
* @author 李
* @description 针对表【dish(菜品管理)】的数据库操作Service
* @createDate 2022-09-07 10:23:45
*/
public interface DishService extends MPJBaseService<Dish> {

    /**
     * 分页查询菜品管理
     * @param pageInfo
     * @param dish
     * @return
     */
    IPage findByPage(Page<DishDto> pageInfo, Dish dish);
}
