package com.workcode.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.config.PageUtils;
import com.workcode.entity.User;
import com.workcode.entity.UserLogin;
import com.workcode.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 铁壮
 * @since 2020-09-01
 */
@RestController
@RequestMapping("/user-login")
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;
    /**
     * 员工登录日志分页controller
     * @param params
     * @return
     */
    @GetMapping(value = "getUserLoginByPage")
    public PageUtils getUserLoginByPage(@RequestParam Map<String, Object> params){
        System.out.println(params+"++++++");
        int userLoginPage = Integer.parseInt(params.get("pageNumber").toString());
        int userLoginLimit = Integer.parseInt(params.get("pageSize").toString());
        Page<UserLogin> userPage = new Page<>(userLoginPage, userLoginLimit);
        userLoginService.getUserLoginByPage(userPage,params);
        PageUtils pages = new PageUtils(userPage.getRecords(), (int) userPage.getTotal());
        return pages;
    }
}

