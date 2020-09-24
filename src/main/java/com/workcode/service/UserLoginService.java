package com.workcode.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.entity.UserLogin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 铁壮
 * @since 2020-09-01
 */
public interface UserLoginService extends IService<UserLogin> {
    /**
     * 员工登录日志分页controller
     * @param userLoginPage
     * @param params
     */
    void getUserLoginByPage(Page<UserLogin> userPage, Map<String, Object> params) throws ParseException;

    byte[] exportCsv(String user_id,String startData,String endData) throws ParseException;
}
