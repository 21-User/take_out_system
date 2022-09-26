package com.lht.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lht.pojo.dto.DishDto;
import com.lht.pojo.entity.Dish;
import com.lht.pojo.vo.Result;
import com.lht.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.List;

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


    /**
     * 添加菜品
     * @param dishDto
     * @return
     */
    @PostMapping
    public Result<String> add(@RequestBody DishDto dishDto ) {

        return dishService.saveWithFlavor(dishDto);
    }

    @GetMapping("/page")
    public Result<IPage<DishDto>> page(@RequestParam Integer page, @RequestParam Integer pageSize, Dish dish) {

        Page<DishDto> pageInfo = new Page<>(page, pageSize);

        dishService.findByPage(pageInfo, dish);

        return Result.success(pageInfo);
    }

    /**
     * 通过id查询菜品和对应的口味
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<DishDto> getById(@PathVariable("id") Long id) {

        return dishService.getByIdWithFlavor(id);
    }

    /**
     * 修改和批量修改菜品状态
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result<Dish> EditStatus(@PathVariable Integer status, @RequestParam List<Long> id) {

        return dishService.batchEditStatus(status, id);
    }

    /**
     * 删除和批量删除菜品
     * @return
     */
    @DeleteMapping("/id")
    public Result<Dish> delDish(List<Long> id) {

        return dishService.delDish(id);
    }


}
