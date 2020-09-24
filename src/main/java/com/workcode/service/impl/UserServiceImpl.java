package com.workcode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.config.CsvUtil;
import com.workcode.entity.User;
import com.workcode.mapper.UserMapper;
import com.workcode.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 铁壮
 * @since 2020-08-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    SimpleDateFormat Time3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void getUserByPage(Page<User> userPage, Map<String, Object> params) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
       // wrapper.orderByAsc("sort");
        //如果没有条件，就只做简单的查询
       if(params.get("user_id") == null){
            baseMapper.selectPage(userPage, wrapper);
            return ;
               }
        wrapper.like("user_id", params.get("user_id").toString());
        baseMapper.selectPage(userPage, wrapper);
        //如果有条件，获取条件参数，把条件加到wrapper中
       /* String name = user.getName();
        Integer level = user.getLevel();
        String begin = queryTeacher.getBegin();
        String end = queryTeacher.getEnd();*/

        //判断各参数是否有值，没有值，不管了；有值，把值加在wrapper中
       /* if(!StringUtils.isEmpty(name)){
            wrapper.like("name", name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level", level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create", begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create", end);

        }
        baseMapper.selectPage(pageParam, wrapper);*/

    }

    @Override
    public byte[] exportCsv(String user_id) {
        List<Map<String, Object>> mapList = null;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
       // 如果没有条件，就只做简单的查询
        if (user_id==null){
             mapList = this.baseMapper.selectMaps(wrapper);
        }else {
            wrapper.like("user_id", user_id);
             mapList = this.baseMapper.selectMaps(wrapper);
        }

        byte[] content = null;
        try {
            String[] sTitles = new String[]{"id","员工账号","员工密码","创建时间","更新时间"};
            String[] mapKeys = new String[]{"id","user_id","user_password","create_time","update_time"};

            ByteArrayOutputStream os = CsvUtil.doExport(sTitles,mapKeys, mapList);
            content = os.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return content;
    }
}
