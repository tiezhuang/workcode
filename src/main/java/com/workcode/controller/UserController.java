package com.workcode.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.config.CsvUtil;
import com.workcode.config.PageUtils;
import com.workcode.config.R;
import com.workcode.entity.User;
import com.workcode.service.UserService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        Page<User> userPage = new Page<>(page, limit);
       userService.getUserByPage(userPage,params);
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

    /**
     * 添加员工
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    public ModelAndView addUser(@RequestParam(value = "user_id",required = false) String user_id){
        boolean parsable = NumberUtils.isParsable(user_id);
        ModelAndView mv = new ModelAndView();
        if(parsable){

            if (userService.add(user_id).getMessage().equals("成功")){
                mv.setViewName("redirect:/jump/addSuccess");
                return mv;
            }
            mv.setViewName("redirect:/jump/addFail");
            return mv;
        }
        mv.setViewName("redirect:/jump/addSpecs");
        return mv;
    }

    /**
     * 批量添加员工
     * @param excelFile
     * @return
     */
    @PostMapping("/addUsers")
    public R addUser(@RequestParam MultipartFile[] file){
        System.out.println(file);
        try {
            userService.addUsers(file[0]);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok();
    }

    /**
     * 下载员工模板
     * @param response
     */
    @GetMapping("/downloadExce")
    public void downloadExce(HttpServletResponse response){
        try {
            userService.downloadExces(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

