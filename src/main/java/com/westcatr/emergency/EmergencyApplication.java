package com.westcatr.emergency;

import com.westcatr.rd.base.aredis.annotation.EnableICacheKey;
import com.westcatr.rd.base.authority.annotation.EnableIAuthority;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableCaching
@EnableICacheKey
@EnableIAuthority
@EnableScheduling
@MapperScan("com.westcatr.emergency.business.mapper")
@SpringBootApplication
public class EmergencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmergencyApplication.class, args);
    }

}
