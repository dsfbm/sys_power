package com.study.sys_power.service;
import com.study.sys_power.base.result.Results;
import com.study.sys_power.dto.RoleDto;
import com.study.sys_power.model.SysRole;


public interface RoleService {

    Results<SysRole> getAllRoles();

    Results<SysRole> getAllRolesByPage(Integer offset, Integer limit);

    Results<SysRole> save(RoleDto roleDto);

    SysRole getRoleById(Integer id);

    Results update(RoleDto roleDto);

    Results delete(Integer id);

    Results<SysRole> getRoleByFuzzyRoleNamePage(String roleName, Integer offset, Integer limit);

}
