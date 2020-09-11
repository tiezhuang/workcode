package com.workcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jump")
public class jumpController {
    /**
     * 跳转电脑日志页面
     * @return
     */
    @GetMapping(value = "getFile")
    public String getFile(){

        return "admin/file_page";
    }

    /**
     * 跳转员工登录日志页面
     * @return
     */
    @GetMapping(value = "userLogin")
    public String userLogin(){

        return "admin/employee_login";
    }
    /**
     * 跳转电脑日志页面
     * @return
     */
    @GetMapping(value = "download")
    public String download(){

        return "admin/download";
    }
    /**
     * 跳转员工账号页面
     * @return
     */
    @GetMapping(value = "getUser")
    public String getUser(){

        return "index";
    }
}
