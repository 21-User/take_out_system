package com.lht.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lht.pojo.entity.Employee;
import com.lht.pojo.vo.Result;
import com.lht.service.EmployeeService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 员工信息控制器
 * @Author lihetao
 * @Date 2022/9/2 11:31
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 登录
     * @param employee
     * @param req
     * @return
     */
    @PostMapping("/login")
    public Result<Employee> login(@RequestBody Employee employee, HttpServletRequest req) {
        //获取到前端提交的密码
        String password = employee.getPassword();

        //将密码使用md5进行加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //根据用户名查询数据库
        LambdaQueryWrapper<Employee> qw = new LambdaQueryWrapper<>();

        qw.eq(Employee::getUsername, employee.getUsername());

        //根据用户名查询到的员工信息
        Employee empData = employeeService.getOne(qw);

        if (empData == null) {
            return Result.error("账号不存在");
        }

        //加密后的密码和数据库密码equals
        if (!password.equals(empData.getPassword())) {
            return Result.error("密码错误请重新登录！");
        }

        //判断用户是否已经用
        if (empData.getStatus() == 0) {
            return Result.error("账号已禁用！");
        }

        //登录成功之后将员工信息存放到域对象中
        req.getSession().setAttribute("employee", empData.getId());

        return Result.success(empData);
    }
}
