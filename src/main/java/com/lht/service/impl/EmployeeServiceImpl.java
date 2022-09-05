package com.lht.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lht.pojo.entity.Employee;
import com.lht.pojo.vo.Result;
import com.lht.service.EmployeeService;
import com.lht.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 添加员工的实习类
* @author 李
* @description 针对表【employee(员工信息)】的数据库操作Service实现
* @createDate 2022-09-02 10:24:34
*/
@Service
@Slf4j
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Result<Employee> add (HttpServletRequest req, Employee employee) {

        //将密码进行md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("password".getBytes()));

        //获取域对象中的员工信息
        Long empId =(Long) req.getSession().getAttribute("employee");

        employee.setCreateTime(new Date());
        employee.setCreateUser(empId);
        employee.setUpdateTime(new Date());
        employee.setUpdateUser(empId);

        int affectRows = employeeMapper.insert(employee);

        if (affectRows > 0) {
            return Result.success(employee);
        }

        return Result.error("添加失败");
    }

    /**
     * 分页查询员工信息接口的实现类
     * @param employee
     * @param pageInfo
     * @return
     */
    @Override
    public IPage findAll(Employee employee, Page<Employee> pageInfo) {

        //条件构造器
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();

        //根据名字模糊 查询
        lqw.like(StringUtils.isNotEmpty(employee.getName()), Employee::getName, employee.getName());

        lqw.orderByDesc(Employee::getCreateTime);

        Page<Employee> pageData = this.page(pageInfo, lqw);

        return pageData;
    }

    @Override
    public Result<Employee> edit(HttpServletRequest req, Employee employee) {

        Long empId =(Long) req.getSession().getAttribute("employee");

        employee.setUpdateUser(empId);
        employee.setUpdateTime(new Date());

        int affectedRows = employeeMapper.updateById(employee);

        if (affectedRows > 0) {
            return Result.success(employee);
        }
        return Result.error("修改失败！");
    }
}




