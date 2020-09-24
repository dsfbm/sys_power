package com.study.sys_power.service;

import com.study.sys_power.base.result.Results;
import com.study.sys_power.model.SysUser;

public interface DepartmentService {
    Results<SysUser> getAllUsersByPage(Integer offset, Integer limit,Integer branch);

}
