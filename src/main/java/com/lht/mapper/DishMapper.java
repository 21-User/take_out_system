package com.lht.mapper;

import com.lht.pojo.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 李
* @description 针对表【dish(菜品管理)】的数据库操作Mapper
* @createDate 2022-09-07 10:23:45
* @Entity com.lht.pojo.entity.Dish
*/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}




