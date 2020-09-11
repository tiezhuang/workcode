package com.workcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*@EnableTransactionManagement(proxyTargetClass = true) //开启事务支持,然后在访问数据库的Service方法上添加注解@Transactional便可*/
@MapperScan(basePackages = { "com.workcode.mapper","com.workcode.config" }) //Mapper类包扫描
@SpringBootApplication
@Configuration
@EnableTransactionManagement
public class WorkcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkcodeApplication.class, args);
    }

}
