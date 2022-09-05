package com.lht.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lht.pojo.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lht.pojo.vo.Result;

import javax.servlet.http.HttpServletRequest;

/**
* @author 李
* @description 针对表【employee(员工信息)】的数据库操作Service
* @createDate 2022-09-02 10:24:34
*/
public interface EmployeeService extends IService<Employee> {

    /**
     * 新增员工信息接口
     * @param req
     * @param employee
     * @return
     */
    Result<Employee> add(HttpServletRequest req, Employee employee);

    /**
     * 分页查询员工信息接口
     * @param employee
     * @param pageInfo
     * @return
     */
    IPage findAll(Employee employee, Page<Employee> pageInfo);

    /**
     * 修改员工信息接口
     * @param req
     * @param employee
     */
    Result<Employee> edit(HttpServletRequest req, Employee employee);
}
