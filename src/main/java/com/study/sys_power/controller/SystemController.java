package com.study.sys_power.controller;

import com.study.sys_power.oshi.SystemHardwareInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/system")
@Slf4j
public class SystemController {
    /**
     * 系统硬件信息页面
     */
    @RequestMapping("/systemInfo")
    public String systemInfo(Model model){
        SystemHardwareInfo systemHardwareInfo = new SystemHardwareInfo();
        systemHardwareInfo.copyTo();
        model.addAttribute("server",systemHardwareInfo);
        return "systemInfo/systemInfo";
    }
}
