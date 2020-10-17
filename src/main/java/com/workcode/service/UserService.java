package com.workcode.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.config.R;
import com.workcode.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 铁壮
 * @since 2020-08-31
 */
public interface UserService extends IService<User> {

    void getUserByPage(Page<User> userPage, Map<String, Object> params);

    /**
     * 下载员工账号
     * @return
     */
    byte[] exportCsv(String user_id);

    /**
     * 添加员工
     * @param user
     * @return
     */
    R add(String user_id);

    /**
     * 批量添加员工
     * @param excelFile
     */
    void addUsers(MultipartFile excelFile) throws Exception;

    /**
     * 下载员工导入模板
     * @param response
     */
    void downloadExces(HttpServletResponse response) throws IOException;
}
