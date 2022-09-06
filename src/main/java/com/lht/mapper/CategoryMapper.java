package com.lht.mapper;

import com.lht.pojo.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 李
* @description 针对表【category(菜品及套餐分类)】的数据库操作Mapper
* @createDate 2022-09-06 14:06:18
* @Entity com.lht.pojo.entity.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




