package com.workcode.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.config.CsvUtil;
import com.workcode.config.PageUtils;
import com.workcode.entity.User;
import com.workcode.entity.UserLogin;
import com.workcode.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    /**
     * 根据员工Id导出csv
     * @param user_id
     * @return
     */
    @GetMapping("/userCsv")
    public ResponseEntity<byte[]> exportCsv(@RequestParam(value = "user_id",required = false) String user_id){
        System.out.println(user_id+"+++++++++");

        //设置excel文件名
        String fileName="员工登录日志";
        //设置HttpHeaders，设置fileName编码，排除导出文档名称乱码问题
        HttpHeaders headers = CsvUtil.setCsvHeader(fileName);
        byte[] value = null;
        try {
            //获取要导出的数据
            value = this.userLoginService.exportCsv(user_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(value,headers, HttpStatus.OK);
    }
}

