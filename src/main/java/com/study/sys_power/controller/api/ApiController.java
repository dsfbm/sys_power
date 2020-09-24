package com.study.sys_power.controller.api;

import com.study.sys_power.controller.DepartmentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired
   private DepartmentController departmentController;

    @RequestMapping("/getPage")
    public ModelAndView getPage(ModelAndView modelAndView, String pageName,Integer branch){
        departmentController.setBranch(branch);
        modelAndView.setViewName(pageName);
        return modelAndView;
    }

}
