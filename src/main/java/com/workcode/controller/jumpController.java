package com.workcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 跳转到添加员工的页面
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(){
        return "admin/register";
    }
    /**
     *添加成功后跳转到添加后的页面
     */
    @RequestMapping("/addSuccess")
    public String addSuccess(HttpServletRequest request){
        request.setAttribute("msg", "添加成功");
        return "admin/register";
    }
    /**
     *添加失败后跳转到添加后的页面
     */
    @RequestMapping("/addFail")
    public String addFail(HttpServletRequest request){
        request.setAttribute("msg", "添加失败，员工账号已经存在");
        return "admin/register";
    }
    /**
     *添加的账号不符合规格
     */
    @RequestMapping("/addSpecs")
    public String addSpecs(HttpServletRequest request){
        request.setAttribute("msg", "添加失败，添加的员工账号不符合规范");
        return "admin/register";
    }
}
