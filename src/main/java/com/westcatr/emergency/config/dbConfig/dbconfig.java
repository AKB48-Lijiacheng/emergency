/*
 * @Description: In User Settings Edit
 * @Author: your name
 * @Date: 2019-06-05 17:39:30
 * @LastEditTime: 2019-08-21 14:06:52
 * @LastEditors: Please set LastEditors
 */
package com.westcatr.emergency.config.dbConfig;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @ClassName dbconfig
 * @Description TODO
 * @Author LiuSheng
 * @Date 2019/5/22 14:21
 * @Version 1.0
 **/
@Configuration
public class dbconfig {

    @Primary
    @Bean
    @Qualifier("em")
    @ConfigurationProperties(prefix = "spring.datasource.em")
    public DataSource emDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }






    @Bean
    @Qualifier("h3")
    @ConfigurationProperties(prefix = "spring.datasource.h3")
    public DataSource h3DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }


    @Bean(name = "h3Jdbc")
    @Qualifier("h3JdbcTemplate")
    public JdbcTemplate secondJdbcTemplate(@Qualifier("h3") DataSource dataSource) {
        return new JdbcTemplate(dataSource);

    }
}
