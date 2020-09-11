package com.workcode.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
