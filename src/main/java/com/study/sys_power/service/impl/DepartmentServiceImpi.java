package com.study.sys_power.service.impl;

import com.study.sys_power.base.result.Results;
import com.study.sys_power.dao.DepartmentDao;
import com.study.sys_power.dao.UserDao;
import com.study.sys_power.model.SysUser;
import com.study.sys_power.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpi implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;
    @Override
    public Results<SysUser> getAllUsersByPage(Integer offset, Integer limit,Integer branch) {
        //count user-list
        return Results.success(departmentDao.countAllUsers().intValue(), departmentDao.getAllUsersByPage(offset, limit,branch));
    }
}
