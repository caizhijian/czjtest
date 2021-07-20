package com.czj.module;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author:caizhijian
 * @Date:2021-07-20
 */
@SpringBootApplication
@MapperScan("com.czj.module.tender.mapper")
public class CzjTestAPP {

    public static void main(String[] args) {
        SpringApplication.run(CzjTestAPP.class,args);
    }
}
