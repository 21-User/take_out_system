package com.lht.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.lht.pojo.dto.DishDto;
import com.lht.pojo.entity.Category;
import com.lht.pojo.entity.Dish;
import com.lht.pojo.entity.DishFlavor;
import com.lht.pojo.vo.Result;
import com.lht.service.DishFlavorService;
import com.lht.service.DishService;
import com.lht.mapper.DishMapper;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author 李
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2022-09-07 10:23:45
*/
@Service
public class DishServiceImpl extends MPJBaseServiceImpl<DishMapper, Dish>
    implements DishService{

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 分页查询全部菜品
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

    /**
     * 新增菜品和菜品口味
     * @param dishDto
     * @return
     */
    @Override
    @Transactional
    public Result<String> saveWithFlavor(DishDto dishDto) {

        //插入菜品表中有的数据
        dishMapper.insert(dishDto);

        //获取菜品的id
        Long dishId = dishDto.getId();

        //获取多个菜品口味集合
        List<DishFlavor> flavors = dishDto.getFlavors();

        flavors = flavors.stream().map((dishFlavor) -> {
            dishFlavor.setDishId(dishId);

            return dishFlavor;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);

        return Result.success("新增菜品成功！");
    }

    /**
     * 通过id查询菜品和对应的口味接口
     * @param id
     * @return
     */
    @Override
    public Result<DishDto> getByIdWithFlavor(Long id) {

        //根据id获取菜品对象
        Dish dish = getById(id);

        DishDto dishDto = new DishDto();

        //将根据id查询出来的菜品copy到dishDto中
        BeanUtils.copyProperties(dish, dishDto);

        //条件构造器
        LambdaQueryWrapper<DishFlavor> lqw = new LambdaQueryWrapper<>();

        //校验菜品口味关联表中的dishId是否和菜品的id相同
        lqw.eq(DishFlavor::getDishId, dish.getId());

        //根据条件查询
        List<DishFlavor> flavors = dishFlavorService.list(lqw);

        //将查询出来的集合对象set到dishDto 中
        dishDto.setFlavors(flavors);

        return Result.success(dishDto);
    }

    @Override
    public Result<DishDto> editWithFlavor(DishDto dishDto) {

        //更新dish
        updateById(dishDto);

        //删除dishFlavor
        LambdaQueryWrapper<DishFlavor> lqw = new LambdaQueryWrapper<>();

        lqw.eq(DishFlavor::getDishId, dishDto.getId());

        dishFlavorService.remove(lqw);

        //插入dishFlavor
        List<DishFlavor> flavors = dishDto.getFlavors();

        //遍历后setId
        flavors.stream().map((item) ->{
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);

        return Result.success(dishDto);
    }

    /**
     * 修改和批量修改菜品状态
     * @param status
     * @param id
     * @return
     */
    @Override
    public Result<Dish> batchEditStatus(Integer status, List<Long> id) {

        LambdaQueryWrapper<Dish> lqw = new LambdaQueryWrapper<>();

        //多个id不为空，且选中的菜品的id和id相同
        lqw.in(id != null, Dish::getId, id);

        List<Dish> dishes = list(lqw);

        for (Dish dish : dishes) {
            dish.setStatus(status);

            boolean update = updateById(dish);

            if (update) {
                return Result.success(dish);
            }
        }

        return Result.error("批量修改菜品状态失败！！！");
    }

    @Override
    public Result<Dish> delDish(List<Long> id) {



        return null;
    }

}




