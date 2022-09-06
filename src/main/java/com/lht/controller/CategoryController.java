package com.lht.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lht.pojo.entity.Category;
import com.lht.pojo.vo.Result;
import com.lht.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 分类管理控制器
 * @Author lihetao
 * @Date 2022/9/6 14:08
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 添加分类
     * @param category
     * @return
     */
    @PostMapping
    public Result<Category> add(@RequestBody Category category) {

        boolean save = categoryService.save(category);

        if (!save) {
            return Result.error("新增套餐分类失败！");
        }

        return Result.success(category);
    }

    @GetMapping("/page")
    public Result<IPage<Category>> page(@RequestParam Integer page, @RequestParam Integer pageSize, Category category) {

        Page<Category> pageInfo = new Page<>(page, pageSize);

        categoryService.pageAll(category, pageInfo);

        return null;
    }

}
