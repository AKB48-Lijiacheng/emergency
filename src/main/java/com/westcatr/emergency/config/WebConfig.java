package com.westcatr.emergency.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.westcatr.emergency.config.convert.GlobalJsonDateConvert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


@Configuration
public class WebConfig {

    //JSON格式 全局日期转换器配置，LongId转换
    @Bean
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter()  {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //设置日期格式
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(GlobalJsonDateConvert.instance);
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //设置长Id转换器
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);

        return mappingJackson2HttpMessageConverter;
    }
}

