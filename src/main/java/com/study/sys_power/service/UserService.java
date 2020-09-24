package com.study.sys_power.service;


import com.study.sys_power.base.result.Results;
import com.study.sys_power.dto.UserDto;
import com.study.sys_power.model.SysUser;
import com.study.sys_power.model.SysUserMap;

import java.util.List;

public interface UserService {
	SysUser getUser(String username);


	Results<SysUser> getAllUsersByPage(Integer offset, Integer limit);

	Results save(SysUser userDto, Integer roleId);

	SysUser getUserByPhone(String telephone);

	SysUser getUserById(Long id);

	Results<SysUser> updateUser(UserDto userDto, Integer roleId);

	int deleteUser(Long id);

	Results<SysUser> getUserByFuzzyUsername(String username, Integer offset, Integer limit);
	List<SysUserMap> findAll();
}
