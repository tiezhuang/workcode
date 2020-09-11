package com.workcode.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.config.PageUtils;
import com.workcode.entity.FileTest;
import com.workcode.service.FileTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/file-test")
public class FileTestController {
    @Autowired
    private FileTestService fileTestService;
    /**
     * 分页获取电脑记录
     * @param fileParams
     * @return
     */
    @GetMapping(value = "getFileByPage")
    public PageUtils getFileByPage(@RequestParam Map<String, Object> fileParams){
        System.out.println(fileParams+"++++");
        int filePage = Integer.parseInt(fileParams.get("pageNumber").toString());
        int fileLimit = Integer.parseInt(fileParams.get("pageSize").toString());
        Page<FileTest> fileTestPagePage = new Page<>(filePage, fileLimit);
        fileTestService.getFileByPage(fileTestPagePage,fileParams);
        PageUtils pages = new PageUtils(fileTestPagePage.getRecords(), (int) fileTestPagePage.getTotal());
        return pages;
    }

}

