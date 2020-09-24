package com.workcode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.config.CsvUtil;
import com.workcode.entity.UserLogin;
import com.workcode.mapper.UserLoginMapper;
import com.workcode.service.UserLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    SimpleDateFormat Time3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void getUserLoginByPage(Page<UserLogin> userPage, Map<String, Object> params) throws ParseException {

        QueryWrapper<UserLogin> wrapper = new QueryWrapper<>();
        // wrapper.orderByAsc("sort");
        String user_id = params.get("user_id").toString();
        String startData = params.get("startData").toString();
        String endData = params.get("endData").toString();
        String endData1 = "";
        String startData1 = "";
        if(startData!=""){
             startData1 = Time3.format(SDF.parse(startData));
        }else {
             startData1="";
        }
        if(endData!=""){
             endData1 = Time3.format(SDF.parse(endData));
        }else {
             endData1="";
        }
         if(!StringUtils.isEmpty(user_id)){
            wrapper.like("user_id", user_id);
        }
        if(!StringUtils.isEmpty(startData1)){
            wrapper.ge("login_time", startData1);
        }
        if(!StringUtils.isEmpty(endData1)){
            wrapper.le("login_time", endData1);
        }
        baseMapper.selectPage(userPage, wrapper);
    }

    @Override
    public byte[] exportCsv(String user_id,String startData,String endData) throws ParseException {
        List<Map<String, Object>> mapList = null;
        QueryWrapper<UserLogin> wrapper = new QueryWrapper<>();

        String endData1 = "";
        String startData1 = "";
        if(startData!=""){
            startData1 = Time3.format(SDF.parse(startData));
        }else {
            startData1="";
        }
        if(endData!=""){
            endData1 = Time3.format(SDF.parse(endData));
        }else {
            endData1="";
        }
        if(!StringUtils.isEmpty(user_id)){
            wrapper.like("user_id", user_id);
        }
        if(!StringUtils.isEmpty(startData1)){
            wrapper.ge("login_time", startData1);
        }
        if(!StringUtils.isEmpty(endData1)){
            wrapper.le("login_time", endData1);
        }
            mapList = this.baseMapper.selectMaps(wrapper);

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
