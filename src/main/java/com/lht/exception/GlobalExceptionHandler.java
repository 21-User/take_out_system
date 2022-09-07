package com.lht.exception;

import com.lht.pojo.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;


/**
 * 自定义全局异常处理
 * @author: 21
 * @date: 2022/9/4
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> duplicateKeyException(SQLIntegrityConstraintViolationException e) {
        log.info(e.getMessage());

        if (e.getMessage().contains("Duplicate entry")) {
            return Result.error("您输入的值已存在！");
        }

        return Result.error("账号不能重复！");
    }

    /**
     * 类型转换异常的处理方法
     * @param e
     * @return
     */
    @ExceptionHandler(ClassCastException.class)
    public Result<String> classCastException(ClassCastException e) {
        log.info("异常信息为:{}", e.getMessage());

        return Result.error("类型转换异常");
    }

    @ExceptionHandler(CustomException.class)
    public Result<String> customException(CustomException e) {

        return Result.error(e.getMessage());
    }
}
