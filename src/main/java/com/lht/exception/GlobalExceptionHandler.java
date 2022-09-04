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
                String[] str1 = e.getMessage().split(" ");

            String duplicateKey = str1[2].substring(1, 8);


            String msg = duplicateKey + "已存在请重新输入！";

                System.out.println("测试异常的信息" + msg);

                return Result.error(msg);
        }

        return Result.error("账号不能重复！");
    }
}
