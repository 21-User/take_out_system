package com.lht.pojo.dto;

import com.lht.pojo.entity.Dish;
import lombok.Data;

/**
 * 菜品管理分页查询dto
 * @Author lihetao
 * @Date 2022/9/8 17:06
 */
@Data
public class DishDto extends Dish {

    /**
     * 菜品分类名称
     */
    private String categoryName;
}
