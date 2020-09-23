package com.workcode.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.config.CsvUtil;
import com.workcode.config.PageUtils;
import com.workcode.entity.FileTest;
import com.workcode.service.FileTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    /**
     * 根据员工Id导出csv
     * @param user_id
     * @return
     */
    @GetMapping("/userCsv")
    public ResponseEntity<byte[]> exportCsv(@RequestParam(value = "user_id",required = false) String user_id){
        System.out.println(user_id+"+++++++++");

        //设置excel文件名
        String fileName="员工操作日志";
        //设置HttpHeaders，设置fileName编码，排除导出文档名称乱码问题
        HttpHeaders headers = CsvUtil.setCsvHeader(fileName);
        byte[] value = null;
        try {
            //获取要导出的数据
            value = this.fileTestService.exportCsv(user_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(value,headers, HttpStatus.OK);
    }
}

