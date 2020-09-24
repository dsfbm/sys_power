package com.study.sys_power.controller;

import com.alibaba.fastjson.JSONArray;
import com.study.sys_power.base.result.Results;
import com.study.sys_power.dao.PermissionDao;
import com.study.sys_power.dto.RoleDto;
import com.study.sys_power.model.SysPermission;
import com.study.sys_power.service.PermissionService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("permission")
@Slf4j
public class PermissionController {
    @Autowired
    private PermissionService permissionService;


    /**
     * 新增角色 编辑角色  他所对应的权限（json层级关系）
     * @return json层级关系
     */
    @RequestMapping(value = "/listAllPermission", method = RequestMethod.GET)
    @ResponseBody
    public Results<JSONArray> listAllPermission() {

        return permissionService.listAllPermission();

    }


    /**
     * 编辑这个用户所对应的权限
     * @param roleDto
     * @return
     */
    @RequestMapping(value = "/listAllPermissionByRoleId", method = RequestMethod.GET)
    @ResponseBody     //传递过来的id是如何传递给id的？？？？？
    public Results<SysPermission> listAllPermissionByRoleId(RoleDto roleDto) {

        log.info(getClass().getName() + " : param =  " + roleDto);
        return permissionService.listByRoleId(roleDto.getId().intValue());


    }

    /**
     *
     * @return 获取所有菜单   普通的List<T> 并没有层级关系
     */
    @GetMapping("/menuAll")
    @ResponseBody
    public Results getMenuAll(){
        Results<SysPermission> menuAll = permissionService.getMenuAll();

        return menuAll;
    }

    /**
     *
     * @return 返回最后一个id
     */


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPermission(Model model) {
        model.addAttribute("sysPermission",new SysPermission());
        return "permission/permission-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Results<SysPermission> savePermission(@RequestBody SysPermission permission) {
        Results<SysPermission> save = permissionService.save(permission);
       //调用 插入关联表id方法
      permissionService.insertId();
      return save;
    }

    /**
     * 在点击菜单编辑时 由这里进行跳转到permission/permission-add  然后这里是拿数据的地方
     * @param model
     * @param permission
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPermission(Model model, SysPermission permission) {
        model.addAttribute("sysPermission",permissionService.getSysPermissionById(permission.getId()));
        return "permission/permission-add";
    }

    /**
     *
     * @param permission
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Results updatePermission(@RequestBody  SysPermission permission) {
        return permissionService.updateSysPermission(permission);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Results deletePermission(SysPermission sysPermission) {
        return permissionService.delete(sysPermission.getId());
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @ResponseBody
    public Results<SysPermission> getMenu(Long userId) {
        return permissionService.getMenu(userId);
    }



}
