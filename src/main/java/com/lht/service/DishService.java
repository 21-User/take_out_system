package com.lht.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseService;
import com.lht.pojo.dto.DishDto;
import com.lht.pojo.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lht.pojo.vo.Result;

import java.util.List;

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


    /**
     * 新增菜品和菜品口味接口
     * @param dishDto
     * @return
     */
    Result<String> saveWithFlavor(DishDto dishDto);

    /**
     * 通过id查询菜品和对应的口味接口
     * @param id
     * @return
     */
    Result<DishDto> getByIdWithFlavor(Long id);

    /**
     * 修改菜品和菜品口味接口
     * @param dishDto
     * @return
     */
    Result<DishDto> editWithFlavor(DishDto dishDto);

    /**
     * 修改和批量修改菜品状态接口
     * @param status
     * @param id
     * @return
     */
    Result<Dish> batchEditStatus(Integer status, List<Long> id);

    /**
     * 删除和批量删除菜品接口
     * @param id
     * @return
     */
    Result<Dish> delDish(List<Long> id);
}
