package com.study.sys_power.controller;

import com.study.sys_power.base.result.PageTableRequest;
import com.study.sys_power.base.result.Results;
import com.study.sys_power.dao.DepartmentDao;
import com.study.sys_power.model.SysUser;
import com.study.sys_power.service.DepartmentService;
import com.study.sys_power.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("branch")
@Data
@Slf4j
public class DepartmentController {
    private String branch;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @ResponseBody
    public Results<SysUser> getUsers(PageTableRequest request,String branch) {
        log.info("UserController.getUsers(): param ( request1 = " + request +" )");
        branch=this.branch;
        request.countOffset();
        return departmentService.getAllUsersByPage(request.getOffset(), request.getLimit(),branch);
    }
    @GetMapping("/findUserByFuzzyUsername")
    @ResponseBody
    public Results<SysUser> findUserByFuzzyUsername(PageTableRequest request, String username) {
        log.info("UserController.findUserByFuzzyUsername(): param ( request1 = " + request +" ,username = " + username+ ")");
        request.countOffset();
        return userService.getUserByFuzzyUsername(username, request.getOffset(), request.getLimit());
    }

}
