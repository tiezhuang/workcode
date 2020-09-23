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

import java.io.ByteArrayOutputStream;
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

    @Override
    public void getFileByPage(Page<FileTest> fileTestPagePage, Map<String, Object> fileParams) {
        QueryWrapper<FileTest> wrapper = new QueryWrapper<>();
        // wrapper.orderByAsc("sort");
        //如果没有条件，就只做简单的查询
         if(fileParams.get("user_id") == null){
        baseMapper.selectPage(fileTestPagePage, wrapper);
        return ;
          }
        //如果有条件，获取条件参数，把条件加到wrapper中

        wrapper.like("user_id", fileParams.get("user_id").toString());
        baseMapper.selectPage(fileTestPagePage, wrapper);
        //判断各参数是否有值，没有值，不管了；有值，把值加在wrapper中
       /* if(!StringUtils.isEmpty(name)){
            wrapper.like("userId", fileParams.get("searchText"));
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

        List<Map<String, Object>> fileList = null;
        QueryWrapper<FileTest> wrapper = new QueryWrapper<>();
        // 如果没有条件，就只做简单的查询
        if (user_id==null){
            fileList = this.baseMapper.selectMaps(wrapper);
        }else {
            wrapper.like("user_id", user_id);
            fileList = this.baseMapper.selectMaps(wrapper);
        }

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
