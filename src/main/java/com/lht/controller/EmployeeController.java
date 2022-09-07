package com.lht.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lht.pojo.entity.Employee;
import com.lht.pojo.vo.Result;
import com.lht.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 员工信息控制器
 * @Author 21
 * @Date 2022/9/2 11:31
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 登录控制器
     * @param employee
     * @param req
     * @return
     */
    @PostMapping("/login")
    public Result<Employee> login(@RequestBody Employee employee, HttpServletRequest req, HttpServletResponse rsp) {
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

        HttpSession session = req.getSession();

        //登录成功之后将员工信息存放到域对象中
        session.setAttribute("employee", empData.getId());

        //将session添加到cookie中
        Cookie cookie = new Cookie("JSESSIONID", session.getId());

        //设置cookie的过期时间
        cookie.setMaxAge(60*30);

        //将cookie发送到浏览器
        rsp.addCookie(cookie);

        return Result.success(empData);
    }

    /**
     * 退出控制器
     * @param req
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest req) {

        //清楚域对象中的信息
        req.getSession().removeAttribute("employee");

        return Result.success("退出成功！");
    }

    /**
     * 新增员工信息的控制器
     * @param employee
     * @return
     */
    @PostMapping
    public Result<Employee> add(@RequestBody Employee employee) {
        log.info("新增的员工信息{}", employee);

        return employeeService.add (employee);
    }

    /**
     * 分页查询员工信息的控制器
     * @param page
     * @param pageSize
     * @param employee
     * @return
     */
    @GetMapping("/page")
    public Result<IPage<Employee>> findAll(@RequestParam Integer page,@RequestParam Integer pageSize, Employee employee) {

        Page<Employee> pageInfo = new Page<>(page, pageSize);

        employeeService.findAll(employee, pageInfo);

        return Result.success(pageInfo);
    }

    /**
     * 修改员工信息的控制器
     * @return
     */
    @PutMapping
    public Result<Employee> update(HttpServletRequest req, @RequestBody Employee employee) {

        return employeeService.edit(req, employee);
    }

    @GetMapping({"/{id}"})
    public Result<Employee> getOne(@PathVariable("id") Long id) {

        Employee employee = employeeService.getById(id);

        if (employee == null) {
            return Result.error("获取失败！");
        }

        return Result.success(employee);
    }
}
