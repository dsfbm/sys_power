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
    private Integer branch;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    @ResponseBody
    public Results<SysUser> getUsers(PageTableRequest request,Integer branch) {
        log.info("UserController.getUsers(): param ( request1 = " + request +" )");
        branch=this.branch;
        request.countOffset();
        return departmentService.getAllUsersByPage(request.getOffset(), request.getLimit(),branch);
    }



}
