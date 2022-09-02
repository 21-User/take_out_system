package com.lht.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lht.pojo.entity.Employee;
import com.lht.service.EmployeeService;
import com.lht.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author 李
* @description 针对表【employee(员工信息)】的数据库操作Service实现
* @createDate 2022-09-02 10:24:34
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}




