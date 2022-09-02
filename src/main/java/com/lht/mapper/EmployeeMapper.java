package com.lht.mapper;

import com.lht.pojo.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 李
* @description 针对表【employee(员工信息)】的数据库操作Mapper
* @createDate 2022-09-02 10:24:34
* @Entity com.lht.pojo.entity.Employee
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}




