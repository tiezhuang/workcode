package com.workcode.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.config.CsvUtil;
import com.workcode.config.PageUtils;
import com.workcode.entity.User;
import com.workcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 铁壮
 * @since 2020-08-31
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户分页controller
     * @param params
     * @return
     */
    @GetMapping(value = "getUserByPage")
    public PageUtils getUserByPage(@RequestParam Map<String, Object> params){
        int page = Integer.parseInt(params.get("pageNumber").toString());
        int limit = Integer.parseInt(params.get("pageSize").toString());
        System.out.println(params+"+++++++9");
        Page<User> userPage = new Page<>(page, limit);
       userService.getUserByPage(userPage,params);
        PageUtils pages = new PageUtils(userPage.getRecords(), (int) userPage.getTotal());
        return pages;
    }

    @GetMapping("/userCsv")
    public ResponseEntity<byte[]> exportCsv(@RequestParam(value = "user_id",required = false) String user_id){
        System.out.println(user_id+"+++++++++");

        //设置excel文件名
        String fileName="员工账号";
        //设置HttpHeaders，设置fileName编码，排除导出文档名称乱码问题
        HttpHeaders headers = CsvUtil.setCsvHeader(fileName);
        byte[] value = null;
        try {
            //获取要导出的数据
            value = this.userService.exportCsv(user_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(value,headers, HttpStatus.OK);
    }

}

