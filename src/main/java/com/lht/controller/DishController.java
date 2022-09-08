package com.lht.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lht.pojo.dto.DishDto;
import com.lht.pojo.entity.Dish;
import com.lht.pojo.vo.Result;
import com.lht.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜品管理控制器
 * @Author lihetao
 * @Date 2022/9/7 10:24
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/page")
    public Result<IPage<DishDto>> page(@RequestParam Integer page, @RequestParam Integer pageSize, Dish dish) {

        Page<DishDto> pageInfo = new Page<>(page, pageSize);

        dishService.findByPage(pageInfo, dish);

        return Result.success(pageInfo);
    }
}
