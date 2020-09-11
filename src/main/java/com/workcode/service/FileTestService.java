package com.workcode.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.entity.FileTest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>66666
 *
 * @author 铁壮
 * @since 2020-08-31
 */

public interface FileTestService extends IService<FileTest> {
    /**
     *
     * @param fileTestPagePage
     */
    void getFileByPage(Page<FileTest> fileTestPagePage, Map<String, Object> fileParams);
}
