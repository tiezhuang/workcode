package com.workcode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.entity.UserLogin;
import com.workcode.mapper.UserLoginMapper;
import com.workcode.service.UserLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 铁壮
 * @since 2020-09-01
 */
@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLogin> implements UserLoginService {

    @Override
    public void getUserLoginByPage(Page<UserLogin> userPage, Map<String, Object> params) {

        QueryWrapper<UserLogin> wrapper = new QueryWrapper<>();
        // wrapper.orderByAsc("sort");
        //如果没有条件，就只做简单的查询
        if(params.get("searchText") == null){
            baseMapper.selectPage(userPage, wrapper);
            return ;
        }
        //如果有条件，获取条件参数，把条件加到wrapper中

        wrapper.like("user_id", params.get("searchText").toString());
        baseMapper.selectPage(userPage, wrapper);

    }
}
