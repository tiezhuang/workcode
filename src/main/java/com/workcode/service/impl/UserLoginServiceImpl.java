package com.workcode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.config.CsvUtil;
import com.workcode.entity.UserLogin;
import com.workcode.mapper.UserLoginMapper;
import com.workcode.service.UserLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
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
        if(params.get("user_id") == null){
            baseMapper.selectPage(userPage, wrapper);
            return ;
        }
        //如果有条件，获取条件参数，把条件加到wrapper中

        wrapper.like("user_id", params.get("user_id").toString());
        baseMapper.selectPage(userPage, wrapper);

    }

    @Override
    public byte[] exportCsv(String user_id) {
        List<Map<String, Object>> mapList = null;
        QueryWrapper<UserLogin> wrapper = new QueryWrapper<>();
        // 如果没有条件，就只做简单的查询
        if (user_id==null){
            mapList = this.baseMapper.selectMaps(wrapper);
        }else {
            wrapper.like("user_id", user_id);
            mapList = this.baseMapper.selectMaps(wrapper);
        }

        byte[] content = null;
        try {
            String[] sTitles = new String[]{"id","员工账号","mac","ip","登录时间"};
            String[] mapKeys = new String[]{"id","user_id","mac","ip","login_time"};

            ByteArrayOutputStream os = CsvUtil.doExport(sTitles,mapKeys, mapList);
            content = os.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return content;
    }
}
