package com.mqz.online.edit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @author mqz
 * @since 2020年12月11日14:07:17
 * @description
 * @abount https://github.com/DemoMeng
 */
@Configuration
@EnableAutoConfiguration
@MapperScan(basePackages = {"com.mqz.online.edit.web.mapper.**"})
@ComponentScan(basePackages = {"com.mqz.online.edit.**"})
@EnableSwagger2
public class OnlineEditApplication {

    public static void main(String[] args) {
        // 防止xml xxE xxd 注入
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setExpandEntityReferences(false);
        String FEATURE;
        try {
            FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
            dbf.setFeature(FEATURE, true);
            FEATURE = "http://xml.org/sax/features/external-general-entities";
            dbf.setFeature(FEATURE, false);
            FEATURE = "http://xml.org/sax/features/external-parameter-entities";
            dbf.setFeature(FEATURE, false);
            FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
            dbf.setFeature(FEATURE, false);
            dbf.setXIncludeAware(false);
            dbf.setExpandEntityReferences(false);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        SpringApplication.run(OnlineEditApplication.class, args);
    }

}
