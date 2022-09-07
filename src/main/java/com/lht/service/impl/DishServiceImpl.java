package com.lht.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lht.pojo.entity.Dish;
import com.lht.service.DishService;
import com.lht.mapper.DishMapper;
import org.springframework.stereotype.Service;

/**
* @author 李
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2022-09-07 10:23:45
*/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{

    /**
     * 分页查询菜品
     * @param pageInfo
     * @param dish
     * @return
     */
    @Override
    public IPage findByPage(Page<Dish> pageInfo, Dish dish) {



        return null;
    }
}




