package com.study.sys_power.controller;


import com.study.sys_power.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张清
 * @date 2019/01/08 下午2:43.
 */
@Controller
@RequestMapping(value = "/vis")
public class MapController {

    @Autowired
    private UserService userService;
    /**
     * 柱状图
     * */
    @RequestMapping(value = "/bar", method = RequestMethod.GET)
    public String bar() {
        return "map/bar";
    }

    /**
     * 饼图
     * */
    @RequestMapping(value = "/pie", method = RequestMethod.GET)
    public String pie() {
        return "map/pie";
    }

    /**
     * 折线图
     * */
    @RequestMapping(value = "/line", method = RequestMethod.GET)
    public String line() {
        return "map/line";
    }

    /**
     * 雷达图
     * */
    @RequestMapping(value = "/circle", method = RequestMethod.GET)
    public String circle(){
        return "map/circle";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getList() {
        Map<String,Object> map = new HashMap<>();
        map.put("msg", "ok");
        map.put("data", userService.findAll());
        return map;
    }
}
