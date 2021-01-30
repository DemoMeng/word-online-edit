package com.mqz.online.edit.config.file;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

/**
 * @author mqz
 * @since 2020年12月11日15:18:02
 * @description
 * @abount https://github.com/DemoMeng
 */
@Configuration
public class FileContext {

    @Resource
    private FileConfig fileConfig;

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofBytes(fileConfig.getMaxFileSize()*1024*1024));
        factory.setMaxRequestSize(DataSize.ofBytes(fileConfig.getMaxRequestSize()*1024*1024));
        return factory.createMultipartConfig();
    }


}
