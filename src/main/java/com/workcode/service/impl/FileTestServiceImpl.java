package com.workcode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.workcode.config.CsvUtil;
import com.workcode.entity.FileTest;
import com.workcode.entity.UserLogin;
import com.workcode.mapper.FileTestMapper;
import com.workcode.service.FileTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
 * @since 2020-08-31
 */
@Transactional
@Service
public class FileTestServiceImpl extends ServiceImpl<FileTestMapper, FileTest> implements FileTestService {
    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    SimpleDateFormat Time3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void getFileByPage(Page<FileTest> fileTestPagePage, Map<String, Object> fileParams) throws ParseException {
        QueryWrapper<FileTest> wrapper = new QueryWrapper<>();
        String user_id = fileParams.get("user_id").toString();
        String startData = fileParams.get("startData").toString();
        String endData = fileParams.get("endData").toString();
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
            wrapper.ge("create_time", startData1);
        }
        if(!StringUtils.isEmpty(endData1)){
            wrapper.le("create_time", endData1);
        }
        baseMapper.selectPage(fileTestPagePage, wrapper);

    }

    @Override
    public byte[] exportCsv(String user_id,String  startData,String endData) throws ParseException {

        List<Map<String, Object>> fileList = null;
        QueryWrapper<FileTest> wrapper = new QueryWrapper<>();
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
            wrapper.ge("create_time", startData1);
        }
        if(!StringUtils.isEmpty(endData1)){
            wrapper.le("create_time", endData1);
        }
        fileList = this.baseMapper.selectMaps(wrapper);
        byte[] content = null;
        try {
            String[] sTitles = new String[]{"id","操作记录","mac","工号","操作时间"};
            String[] mapKeys = new String[]{"id","file_action","mac","user_id","create_time"};

            ByteArrayOutputStream os = CsvUtil.doExport(sTitles,mapKeys, fileList);
            content = os.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return content;
    }
}
