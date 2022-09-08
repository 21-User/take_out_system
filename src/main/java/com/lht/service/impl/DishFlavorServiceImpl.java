package com.lht.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lht.pojo.entity.DishFlavor;
import com.lht.service.DishFlavorService;
import com.lht.mapper.DishFlavorMapper;
import org.springframework.stereotype.Service;

/**
* @author 李
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service实现
* @createDate 2022-09-08 15:36:57
*/
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>
    implements DishFlavorService{

}




